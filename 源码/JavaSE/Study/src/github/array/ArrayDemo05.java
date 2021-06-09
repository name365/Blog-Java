package github.array;

/**
 * @author subeiLY
 * @create 2021-05-29 17:31
 */
public class ArrayDemo05 {
    public static void main(String[] args) {
        // [4][2]
        /*
        1,2     array[0]
        2,3     array[1]
        3,4     array[2]
        5,6     array[3]
         */
        int[][] array = {{1,2},{3,4},{5,6},{7,8}};

        // 输出单个元素
        printArray(array[0]);
        System.out.println(array[0][0]);

        System.out.println("----------------");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                System.out.println(array[i][j]);
            }
        }
    }

    // 打印数组元素
    public static void printArray(int[] arrays){
        for(int i=0;i< arrays.length;i++){
            System.out.println(arrays[i]+"");
        }
    }
}
