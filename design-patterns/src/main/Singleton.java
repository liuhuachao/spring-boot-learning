package main;

/**
 * 单例
 * @author liuhuachao
 * @date 2021/12/9
 */
public class Singleton{

	/**
	 * 懒汉模式
	 * 定义一个私有的静态对象instance，之所以定义instance为静态，是因为静态属性或方法是属于类的，
	 * 能够很好地保障单例对象的唯一性；然后定义一个加锁的静态方法获取该对象，
	 * 如果该对象为null，则定义一个对象实例并将其赋值给instance，这样下次再获取该对象时便能够直接获取了。
	 */
	protected static class LazySingleton {
		private static LazySingleton instance;
		private LazySingleton() {}
		public static synchronized LazySingleton getInstance() {
			if (null == instance) {
				instance = new LazySingleton();
			}
			return instance;
		}
	}

	/**
	 * 饿汉模式
	 * 饿汉模式指在类中直接定义全局的静态对象的实例并初始化，然后提供一个方法获取该实例对象。
	 */
	protected static class HungrySingleton{
		private static HungrySingleton instance = new HungrySingleton();
		private HungrySingleton() {}
		public static HungrySingleton getInstance() {
			return instance;
		}
	}

	/**
	 * 双重检查
	 * 双锁模式指在懒汉模式的基础上做进一步优化，给静态对象的定义加上volatile锁来保障初始化时对象的唯一性，在获取对象时通过synchronized(Singleton.class)给单例类加锁来保障操作的唯一性。
	 */
	protected static class Lock2Singleton{
		private volatile static Lock2Singleton instance;
		private Lock2Singleton(){};
		public static Lock2Singleton getInstance(){
			if(null == instance){
				synchronized (Lock2Singleton.class){
					if(null == instance){
						instance = new Lock2Singleton();
					}
				}
			}
			return instance;
		}
	}

	/**
	 * 静态内部类
	 * 静态内部类通过在类中定义一个静态内部类，将对象实例的定义和初始化放在内部类中完成，
	 * 我们在获取对象时要通过静态内部类调用其单例对象。
	 * 之所以这样设计，是因为类的静态内部类在JVM中是唯一的
	 */
	protected static class InnerClassSingleton{
		private static final InnerClassSingleton instance = new InnerClassSingleton();
		private InnerClassSingleton(){}

	}
	public static InnerClassSingleton getInstance(){
		return InnerClassSingleton.instance;
	}
}



