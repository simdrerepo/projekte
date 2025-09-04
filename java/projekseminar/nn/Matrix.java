package nn;
import java.io.Serializable;
import java.util.Random;
import funktionen.Funktion;
public class Matrix implements Serializable {
    int rows;
    int cols;
    double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
        // Standardmäßig 0.0, daher keine weitere Initialisierung nötig
    }

    public static Matrix fromArray(double[] arr) {
        Matrix m = new Matrix(arr.length, 1);
        for (int i = 0; i < arr.length; i++) {
            m.data[i][0] = arr[i];
        }
        return m;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        if (a.rows != b.rows || a.cols != b.cols) {
            System.out.println("Columns and Rows of A must match Columns and Rows of B.");
            return null;
        }

        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                result.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return result;
    }

    public double[] toArray() {
        double[] arr = new double[rows * cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[index++] = data[i][j];
            }
        }
        return arr;
    }

    public void randomize() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = rand.nextDouble() * 2 - 1;
            }
        }
    }

    public void map(Funktion f){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = data[i][j];
                data[i][j] = f.apply(val);
            }
        }
    }

    public static Matrix map(Matrix m, Funktion f){
        Matrix ret = new Matrix(m.rows,m.cols);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                double val = m.data[i][j];
                ret.data[i][j] = f.apply(val);
            }
        }
        return ret;
    }

    public void add(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += n;
            }
        }
    }


    public void add(Matrix n) {
        if (rows != n.rows || cols != n.cols) {
            System.out.println("Columns and Rows of A must match Columns and Rows of B.");
            return;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += n.data[i][j];
            }
        }
    }

    public static Matrix transpose(Matrix matrix) {
        Matrix result = new Matrix(matrix.cols, matrix.rows);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                result.data[j][i] = matrix.data[i][j];
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.cols != b.rows) {
            System.out.println("Columns of A must match rows of B.");
            return null;
        }

        Matrix result = new Matrix(a.rows, b.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public void multiply(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= n;
            }
        }
    }

     public void multiply(Matrix n) {
        // hadamard produkt (elementwise matrix multiplikation)
        if (rows != n.rows || cols != n.cols) {
            System.out.println("Columns and Rows of A must match Columns and Rows of B.");
            return;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= n.data[i][j];
            }
        }
    } 

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }

    
    @Override
    public String toString() {
        String str ="";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols-1; j++) {
                str+=data[i][j]+", ";
            }
            str+=data[i][this.cols-1];
            if(i!=rows-1){
                str+="\n";
            }
        }
        return str;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(rows).append(",").append(cols).append(";");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]);
                if (!(i == rows - 1 && j == cols - 1)) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public static Matrix deserialize(String str) {
        String[] parts = str.split(";");
        String[] dims = parts[0].split(",");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);

        Matrix matrix = new Matrix(rows, cols);
        String[] values = parts[1].split(",");
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.data[i][j] = Double.parseDouble(values[index++]);
            }
        }
        return matrix;
    }
}

