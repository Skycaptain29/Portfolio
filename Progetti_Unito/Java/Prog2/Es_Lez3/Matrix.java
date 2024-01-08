public class Matrix {
    int[][] matrix;

    public Matrix(int i, int j){
        matrix = new int[i][j];
    }

    public int get(int i, int j){
        return matrix[i][j];
    }

    public void set(int i, int j, int n){
        matrix[i][j] = n;
    }

    public int columns(){
        return matrix[0].length;
    }

    public int rows(){
        return matrix.length;
    }

    public Matrix add(Matrix m2){
        Matrix retMatrix = new Matrix(columns(),rows());
        for(int i = 0; i < rows(); i++){
            for(int j = 0; j < columns(); j++){
                retMatrix.set(i,j,matrix[i][j] + m2.get(i,j));
            }
        }
        return retMatrix;
    }

    public Matrix mul(Matrix m2){
        Matrix retMatrix = new Matrix(matrix.length,m2.rows());
        int parzSomma = 0;
        for(int i = 0; i < rows(); i++){
            for(int j = 0; j < m2.columns(); j++){
                for(int k = 0; k < m2.rows(); k++){
                    parzSomma += matrix[i][k] * m2.get(k,j);
                }
                retMatrix.set(i,j, parzSomma);
                parzSomma = 0;
            }
        }
        return retMatrix;
    }

    public Matrix pow(int n){
        Matrix retMatrix;
        for(int i = 0; i < n; i++){
        }
    }

    public void Stampa(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
