/**
 * Integer.parseInt()方法会报错?
 * 答案:会抛出 运行时异常.
 */
public class IntegerParseInt {
    public static void main(String[] args) {
        String str = "12";
        int i = Integer.parseInt(str);
        System.out.println(i);
    }
}
