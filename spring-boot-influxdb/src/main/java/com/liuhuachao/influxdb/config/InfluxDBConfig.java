package com.liuhuachao.influxdb.config;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InfluxDB 参数配置
 * @author liuhuachao
 * @date 2021/12/7
 */
@Configuration
public class InfluxDBConfig {
	@Value("${influxdb.url}")
	private String influxDBUrl;

	@Value("${influxdb.database}")
	private String influxDBDatabase;

	@Value("${influxdb.username}")
	private String influxDBUserName;

	@Value("${influxdb.password}")
	private String influxDBPassword;

	private static String INFLUXDB_URL;

	public static String getInfluxDBUrl() {
		return INFLUXDB_URL;
	}

	private static String INFLUXDB_DATABASE;

	public static String getInfluxDBDatabase() {
		return INFLUXDB_DATABASE;
	}

	private static String INFLUXDB_USERNAME;

	public static String getinfluxDBUserName() {
		return INFLUXDB_USERNAME;
	}

	private static String INFLUXDB_PASSWORD;

	public static String getinfluxDBPassword() {
		return INFLUXDB_PASSWORD;
	}

	private static InfluxDB influxDB;

	public static InfluxDB getInfluxDb() {
		return influxDB;
	}

	@Bean
	public InfluxDB influxdb() {
		INFLUXDB_URL = this.influxDBUrl;
		INFLUXDB_DATABASE = this.influxDBDatabase;
		INFLUXDB_USERNAME = this.influxDBUserName;
		INFLUXDB_PASSWORD = this.influxDBPassword;

		try {
			influxDB = (influxDBUserName != null && influxDBUserName.length() > 0) ? InfluxDBFactory.connect(influxDBUrl, influxDBUserName, INFLUXDB_PASSWORD) : InfluxDBFactory.connect(influxDBUrl);

			if (!influxDB.databaseExists(INFLUXDB_DATABASE)) {
				influxDB.createDatabase(INFLUXDB_DATABASE);
			}

			influxDB.setDatabase(influxDBDatabase)
					.enableBatch(100, 1000 * 60, TimeUnit.NANOSECONDS);

		} catch (Exception e) {
			System.out.println(String.format("InfluxDB 连接异常：%s", e.getMessage()));
		} finally {
			//设置默认策略
			influxDB.setRetentionPolicy("autogen");
		}

		//设置日志输出级别
		influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

		return influxDB;
	}
}
