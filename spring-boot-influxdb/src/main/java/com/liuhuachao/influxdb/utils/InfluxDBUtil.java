package com.liuhuachao.influxdb.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import com.liuhuachao.influxdb.config.InfluxDBConfig;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * InfluxDB 工具类
 * @author liuhuachao
 * @date 2021/12/7
 */
public class InfluxDBUtil {
	//region 字段

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(InfluxDBUtil.class);

	/**
	 * InfluxDB
	 */
	private static InfluxDB influxDB = Optional.ofNullable(InfluxDBConfig.getInfluxDb()).orElseGet(() -> getInfluxDB());

	/**
	 * InfluxDB InfluxDBURL
	 */
	private static final String INFLUXDB_URL = Optional.ofNullable(InfluxDBConfig.getInfluxDBUrl()).orElse("http://127.0.0.1:8086");

	/**
	 * InfluxDB DatabaseName
	 */
	private static final String INFLUXDB_DATABASE = Optional.ofNullable(InfluxDBConfig.getInfluxDBDatabase()).orElse("iot");

	/**
	 * INFLUXDB_USERNAME
	 */
	private static final String INFLUXDB_USERNAME = InfluxDBConfig.getinfluxDBUserName();

	/**
	 * INFLUXDB_PASSWORD
	 */
	private static final String INFLUXDB_PASSWORD = InfluxDBConfig.getinfluxDBPassword();

	/**
	 * 页码
	 */
	private static final int PAGEINDEX = 1;

	/**
	 * 每页个数
	 */
	private static final int PAGESIZE = 100;

	/**
	 * 分页条件
	 */
	private static final String LIMIT = " LIMIT " + PAGESIZE + " OFFSET " + (PAGEINDEX - 1) * PAGESIZE;

	//endregion

	//region 查询

	/**
	 * 查询，返回 Map集合
	 * @param command 完整的查询语句
	 * @return
	 */
	public static List<Map<String, Object>> getListMap(String command) {
		List<Map<String, Object>> results = new ArrayList<>();

		QueryResult queryResult = checkQueryResult(command);
		if (null == queryResult) {
			return results;
		}

		queryResult.getResults().forEach(result -> {
			result.getSeries().forEach(serial -> {
				List<String> columns = serial.getColumns();
				int fieldSize = columns.size();
				serial.getValues().forEach(value -> {
					Map<String, Object> obj = new HashMap<String, Object>();
					for (int i = 0; i < fieldSize; i++) {
						obj.put(columns.get(i), value.get(i));
					}
					results.add(obj);
				});
			});
		});

		return results;
	}

	/**
	 * 查询，返回map集合
	 * @param tableName 表名，不可为空；
	 * @param columns   查询的字段，不可为空；不可为单独的tag
	 * @param where     查询条件
	 * @param order     排序条件
	 * @param pageSize  分页条件
	 * @return
	 */
	public static List<Map<String, Object>> getListMap(@NotNull(message = "InfluxDB 表名不能为空") String tableName, String columns, String where, String order, Integer pageSize, Integer pageIndex) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append(Optional.ofNullable(columns).orElse("*")).append(" FROM ").append("\"" + tableName + "\"");
		query.append(Optional.ofNullable(where).map(x -> " WHERE " + x.replaceAll("where", "")).orElse(""));
		query.append(" ORDER BY ").append(Optional.ofNullable(order).orElse(" time DESC "));
		query.append(Optional.ofNullable(pageSize).map(x -> getLimit(pageIndex, pageSize)).orElse(LIMIT));
		return getListMap(query.toString());
	}

	/**
	 * 查询，返回对象的list集合
	 * @param tableName 表名，不可为空；
	 * @param columns   查询的字段，不可为空；不可为单独的tag
	 * @param where     查询条件
	 * @param order     排序条件
	 * @param pageSize  分页条件
	 * @param clazz     类型
	 * @return
	 */
	public static <T> List<T> getList(@NotNull(message = "InfluxDB 表名不能为空") String tableName, String columns, String where, String order, Integer pageSize, Integer pageIndex, Class<?> clazz) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append(Optional.ofNullable(columns).orElse("*")).append(" FROM ").append("\"" + tableName + "\"");
		query.append(Optional.ofNullable(where).map(x -> " WHERE " + x.replaceAll("where", "")).orElse(""));
		query.append(" ORDER BY ").append(Optional.ofNullable(order).orElse(" time DESC "));
		query.append(Optional.ofNullable(pageSize).map(x -> getLimit(pageIndex, pageSize)).orElse(LIMIT));
		return getList(query.toString(), clazz);
	}

	/**
	 * 查询，返回对象的list集合
	 * @param command
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T> List<T> getList(String command, Class<?> clazz) {
		List results = new ArrayList<>();

		QueryResult queryResult = checkQueryResult(command);
		if (null == queryResult) {
			return results;
		}

		queryResult.getResults().forEach(result -> {
			result.getSeries().forEach(serial -> {
				List<String> columns = serial.getColumns();
				int fieldSize = columns.size();
				serial.getValues().forEach(value -> {
					Object obj = null;
					try {
						obj = clazz.newInstance();
						for (int i = 0; i < fieldSize; i++) {
							String fieldName = columns.get(i);
							Field field = clazz.getDeclaredField(fieldName);
							field.setAccessible(true);
							Class<?> type = field.getType();
							if (type == float.class) {
								field.set(obj, Float.valueOf(value.get(i).toString()));
							}
							else {
								field.set(obj, value.get(i));
							}
						}
					} catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
					results.add(obj);
				});
			});
		});

		return results;
	}

	/**
	 * 默认查询
	 * @param command SQL命令
	 * @param dbNames 数据库名称
	 * @return
	 */
	public static QueryResult query(String command, String... dbNames) {
		String dbName = Optional.ofNullable(INFLUXDB_DATABASE).orElse("iot");
		if (null != dbNames && dbNames.length > 0) {
			dbName = dbNames[0];
		}
		return influxDB.query(new Query(command, dbName));
	}

	/**
	 * 查询所有的表
	 * @return
	 */
	public static List<String> getMeasurements() {
		List<Map<String, Object>> measurements = getListMap("show measurements");
		List<String> tables = measurements.stream().map(x -> x.get("name").toString()).collect(Collectors.toList());
		return tables;
	}

	/**
	 * 检验 QueryResult 是否有结果
	 * @param command
	 * @return
	 */
	private static QueryResult checkQueryResult(String command) {
		QueryResult queryResult = query(command);

		if (queryResult.hasError()) {
			logger.info(queryResult.getError());
		}
		else if (null != queryResult.getResults() && !queryResult.getResults().isEmpty()
				&& null != queryResult.getResults().get(0).getSeries() && !queryResult.getResults().get(0).getSeries().isEmpty()) {
			return queryResult;
		}

		return null;
	}

	/**
	 * 获取分页条件
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	private static String getLimit(int pageIndex, int pageSize) {
		return " LIMIT " + pageSize + " OFFSET " + (pageIndex - 1) * pageSize;
	}

	//endregion

	//region 新增

	/**
	 * 新增单条记录，通过 Java 反射泛型类
	 */
	public static <T> void insertOne(T t) {
		Point.Builder builder = getPointBuilderByReflect(t);
		influxDB.write(builder.build());
	}

	/**
	 * 新增单条记录，通过 tableName 和 fieldsMap
	 * @param tableName
	 * @param fieldsMap
	 */
	public static void insertOne(String tableName, Map<String, Object> fieldsMap, Map<String, String>... tagsMap) {
		Point.Builder builder = getPointBuilderByTableName(tableName, fieldsMap, tagsMap);
		influxDB.write(builder.build());
	}

	/**
	 * 批量新增,通过 List<Map<String,Object>>
	 */
	public static void insertBatchByRecords(String tableName, List<Map<String, Object>> records, List<Map<String, String>> tagsMap) {
		List<String> lines = new ArrayList<String>();

		for (int index = 0; index < records.size(); index++) {
			Point.Builder builder = getPointBuilderByTableName(tableName, records.get(index), !tagsMap.isEmpty() ? tagsMap.get(index) : null);
			lines.add(builder.build().lineProtocol());
		}

		influxDB.write(lines);
	}

	/**
	 * 批量新增,通过 BatchPoints
	 */
	public static <T> void insertBatchByPoints(List<T> records) {
		BatchPoints.Builder batchPoints = BatchPoints.builder();

		records.forEach(record -> {
			Point.Builder builder = getPointBuilderByReflect(record);
			batchPoints.point(builder.build());
		});

		influxDB.write(batchPoints.build());
	}

	/**
	 * 获取 Point.Builder,通过 measurement 和 fieldsMap
	 * @param measurement
	 * @param fieldsMap
	 */
	public static Point.Builder getPointBuilderByTableName(String measurement, Map<String, Object> fieldsMap, Map<String, String>... tagsMap) {
		// 获取 Point.Builder
		Point.Builder pointBuilder = getPointBuilder(measurement, fieldsMap, tagsMap);

		return pointBuilder;
	}

	/**
	 * 获取 Point.Builder,通过反射
	 * @param t
	 * @param <T>
	 * @return
	 */
	@NotNull
	private static <T> Point.Builder getPointBuilderByReflect(T t) {
		// 获取表
		Class<?> clasz = t.getClass();
		Measurement measurement = clasz.getAnnotation(Measurement.class);

		// tags
		Map<String, String> tagsMap = new HashMap<>(4);

		// fields
		Map<String, Object> fieldsMap = new HashMap<>(10);

		// 获取对象属性
		Field[] fieldArray = clasz.getDeclaredFields();
		Column column = null;
		for (Field field : fieldArray) {
			try {
				column = field.getAnnotation(Column.class);
				//设置属性可操作
				field.setAccessible(true);
				if (column.tag()) {
					//tag属性只能存储String类型
					tagsMap.put(column.name(), field.get(t).toString());
				}
				else {
					//设置field
					if (field.get(t) != null) {
						fieldsMap.put(column.name(), field.get(t).toString());
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		// 构建
		Point.Builder pointBuilder = getPointBuilder(measurement.name(), fieldsMap, tagsMap);

		return pointBuilder;
	}

	@NotNull
	private static Point.Builder getPointBuilder(@NotNull(message = "InfluxDB表名不能为空！") String measurement, @NotNull(message = "InfluxDB新增数据不能为空！") Map<String, Object> fieldsMap, Map<String, String>... tagsMap) {
		// 构建数据行
		Point.Builder pointBuilder = Point.measurement(measurement);

		// tags
		if (tagsMap != null) {
			pointBuilder.tag(tagsMap[0]);
		}

		// fields
		pointBuilder.fields(fieldsMap);

		return pointBuilder;
	}

	//endregion

	//region 删除

	/**
	 * 删除表和结构
	 * @param table
	 */
	public static void drop(String table) {
		String dropSQL = "DROP measurement \"" + table + "\"";
		query(dropSQL);
	}

	/**
	 * 删除表数据
	 * @param table
	 */
	public static void delete(String table) {
		if (table == null) {
			throw new IllegalArgumentException();
		}

		String deleteSQL = String.format("DELETE FROM \"%s\"", table);
		query(deleteSQL);
	}

	//endregion

	//region 其他

	/**
	 * 获取连接 InfluxDB
	 * @return
	 */
	public static InfluxDB getInfluxDB() {
		if (null == InfluxDBConfig.getInfluxDb()) {
			influxDB = (null != INFLUXDB_USERNAME && INFLUXDB_USERNAME.length() > 0)
					? InfluxDBFactory.connect(INFLUXDB_URL, INFLUXDB_USERNAME, INFLUXDB_PASSWORD)
					: InfluxDBFactory.connect(INFLUXDB_URL);

			if (!influxDB.databaseExists(INFLUXDB_DATABASE)) {
				influxDB.createDatabase(INFLUXDB_DATABASE);
			}
			influxDB.setDatabase(INFLUXDB_DATABASE);
		}
		return influxDB;
	}

	/**
	 * 创建数据库
	 * @param dbName
	 */
	@SuppressWarnings("deprecation")
	public static void createDatabase(String dbName) {
		influxDB.createDatabase(dbName);
	}

	/**
	 * 删除数据库
	 * @param dbName
	 */
	@SuppressWarnings("deprecation")
	public static void deleteDatabase(String dbName) {
		influxDB.deleteDatabase(dbName);
	}

	/**
	 * 测试连接是否正常
	 */
	public static boolean ping() {
		boolean isConnected = false;
		Pong pong;
		try {
			pong = influxDB.ping();
			if (pong != null) {
				isConnected = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isConnected;
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		if (influxDB != null) {
			influxDB.close();
		}
	}

	/**
	 * 创建默认的保留策略
	 * 默认保留策略名：default_retention_policy，保存天数：30天，保存副本数量：1
	 * 设为默认保留策略
	 */
	public static void createDefaultRetentionPolicy(String database) {
		createRetentionPolicy(database, "default_retention_policy", "30d", 1, true);
	}

	/**
	 * 创建自定义保留策略
	 * @param policyName  策略名
	 * @param duration    保存天数
	 * @param replication 保存副本数量
	 * @param isDefault   是否设为默认保留策略
	 */
	public static void createRetentionPolicy(String database, String policyName, String duration, int replication, Boolean isDefault) {
		String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s ", policyName, database, duration, replication);
		if (isDefault) {
			command = command + " DEFAULT";
		}
		query(command);
	}

	/**
	 * 获取 InfluxDB 的URL和Database
	 * @return
	 */
	public static String getURLAndDataName() {
		return INFLUXDB_URL + "#" + INFLUXDB_DATABASE;
	}

	//endregion
}
