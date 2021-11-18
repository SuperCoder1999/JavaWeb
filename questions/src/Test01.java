/**
 * 测试
 * 1.父类中的方法,子类对象可以直接只用吗
 * 2.父类中的this 是指子类对象还是父类的?
 * 答案:是子类的,原理应该是 this指的是对象实例,父类明显就没有实例,所以一定是子类的
 */
public class Test01 {
    public static void main(String[] args) {
        BB bb = new BB();
        bb.test();
    }
}

class AA {
    public void doGet() {
        System.out.println("父类的 doGet()");
    }
    public void test() {
        this.doGet();
    }
}

class BB extends AA {
    @Override
    public void doGet() {
        System.out.println("子类的doGet()");
    }
}