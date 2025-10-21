package 软件构造.shiyan2;

import java.util.Random;
import java.util.Scanner;

public class MathQuestionGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 选择题目类型：加法，减法，乘法，除法，混合加减法，乘除法
        System.out.println("请选择题目类型：");
        System.out.println("1. 纯加法");
        System.out.println("2. 纯减法");
        System.out.println("3. 纯乘法");
        System.out.println("4. 纯除法");
        System.out.println("5. 加减法混合");
        System.out.println("6. 乘除法混合");
        System.out.println("7. 加减乘除混合");
        int type = scanner.nextInt();

        // 选择操作数的个数：2个数或3个数
        System.out.println("请选择操作数个数：");
        System.out.println("1. 有2个数");
        System.out.println("2. 有3个数");
        int numCount = scanner.nextInt();

        // 选择是否进行3位数运算
        System.out.println("是否生成3位数运算题目？(y/n)");
        boolean isThreeDigit = scanner.next().equalsIgnoreCase("y");

        // 输入生成题目的数量
        System.out.println("请输入题目数量：");
        int questionCount = scanner.nextInt();

        // 输入展示格式
        System.out.println("请输入每行展示题目的个数：");
        int displayPerLine = scanner.nextInt();

        // 生成题目
        generateQuestions(random, type, numCount, isThreeDigit, questionCount, displayPerLine);
    }

    // 生成题目的方法
    public static void generateQuestions(Random random, int type, int numCount, boolean isThreeDigit, int questionCount, int displayPerLine) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < questionCount; i++) {
            // 初始化操作数
            int[] nums = new int[numCount];
            long result = 0;  // 使用 long 类型存储计算结果
            String operator = getOperator(type);  // 在这里获取操作符

            // 生成操作数
            for (int j = 0; j < numCount; j++) {
                if (type == 3 || type == 6 || type == 7) { // 乘法相关题目
                    nums[j] = random.nextInt(20) + 1; // 生成1到20之间的数字，避免乘法结果过大
                } else {
                    nums[j] = random.nextInt(50) + 1; // 对于加法和减法题目，限制数字范围为1到50
                }
            }

            // 判断是否进行三位数运算
            if (isThreeDigit) {
                for (int j = 0; j < numCount; j++) {
                    nums[j] = random.nextInt(900) + 100; // 生成100到999之间的正整数
                }
            }

            try {
                result = performOperation(nums, operator);
            } catch (IllegalArgumentException e) {
                // 如果超出范围，重新生成该题目
                i--;
                continue;
            }

            // 格式化每道题目
            StringBuilder question = new StringBuilder();
            question.append(nums[0]);
            for (int j = 1; j < numCount; j++) {
                question.append(" ").append(operator).append(" ").append(nums[j]);
            }
            question.append(" = ").append(result);

            // 将题目添加到当前行
            line.append(String.format("%-30s", question));  // 控制每个题目占用的空间宽度，确保对齐

            // 如果一行已经有了指定数量的题目，换行
            if ((i + 1) % displayPerLine == 0 || i == questionCount - 1) {
                System.out.println(line.toString().trim());  // 输出当前行的题目
                line.setLength(0);  // 清空当前行
            }
        }
    }

    // 获取操作符
    public static String getOperator(int type) {
        Random random = new Random();
        String operator = "";
        switch (type) {
            case 1: // 纯加法
                operator = "+";
                break;
            case 2: // 纯减法
                operator = "-";
                break;
            case 3: // 纯乘法
                operator = "*";
                break;
            case 4: // 纯除法
                operator = "/";
                break;
            case 5: // 加减法混合
                operator = random.nextBoolean() ? "+" : "-";
                break;
            case 6: // 乘除法混合
                operator = random.nextBoolean() ? "*" : "/";
                break;
            case 7: // 加减乘除混合
                operator = getRandomOperator();
                break;
            default:
                throw new IllegalArgumentException("无效的题目类型");
        }
        return operator;
    }

    // 获取随机运算符（加、减、乘、除）
    public static String getRandomOperator() {
        Random random = new Random();
        int operatorIndex = random.nextInt(4);
        switch (operatorIndex) {
            case 0: return "+";
            case 1: return "-";
            case 2: return "*";
            case 3: return "/";
            default: return "+";
        }
    }

    // 执行运算
    public static long performOperation(int[] nums, String operator) {
        long result = nums[0];  // 使用 long 类型来避免溢出

        for (int i = 1; i < nums.length; i++) {
            switch (operator) {
                case "+":
                    result += nums[i];
                    break;
                case "-":
                    result -= nums[i];
                    break;
                case "*":
                    result *= nums[i];
                    break;
                case "/":
                    if (nums[i] != 0) {
                        result /= nums[i];
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        // 检查结果是否在有效范围内（对于 long 类型结果）
        if (result <= 0 || result >= 100) {
            throw new IllegalArgumentException("Result out of bounds: " + result);
        }

        return result;
    }
}
