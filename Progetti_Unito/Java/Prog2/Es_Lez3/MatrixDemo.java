public class MatrixDemo {
    public static void main(String[] args){
        Matrix matrice = new Matrix(2,2);
        matrice.set(0,0,1);
        matrice.set(0,1,2);
        matrice.set(1,0,3);
        matrice.set(1,1,4);

        Matrix matrice2 = new Matrix(2,2);
        matrice2.set(0,0,1);
        matrice2.set(0,1,2);
        matrice2.set(1,0,3);
        matrice2.set(1,1,4);

        Matrix matriceR = matrice.mul(matrice2);
        matriceR.Stampa();

    }
}
