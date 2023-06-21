import java.util.ArrayList;
import java.util.Arrays;

public class Schachbrett {

    boolean[] brett;
    int size;
    ArrayList<boolean[]> erg=new ArrayList<>();
    public Schachbrett(int size){
        this.size=size;
        this.brett=new boolean[size*size];
        Arrays.fill(this.brett, false);
    }

    public void solve(){
        calculate(0);
    }

    public boolean calculate(int zeile){
        if(zeile>=this.size){
            erg.add(this.brett.clone());
            return false;
        }
        for(int i=0;i<this.size;i++){
            if(addDame(zeile*this.size+i)){
                if(!calculate(zeile+1)){
                    this.brett[zeile*this.size+i]=false;
                }
            }
        }
        return false;
    }

    public boolean pruefeFeld(int feld){
        int prueffeld;
        int spalte=feld%this.size;
        for(int i=0;i<=feld/this.size;i++){

            if(this.brett[spalte+this.size*i]){
                return false;
            }

            prueffeld = feld - i * (this.size + 1);
            if(spalte>prueffeld%this.size&&prueffeld>=0&&this.brett[prueffeld]){
                return false;
            }

            prueffeld = feld - i * (this.size - 1);
            if(spalte<prueffeld%this.size&&this.brett[prueffeld]){
                return false;
            }
        }
        return true;
    }

    public boolean addDame(int feld){
        if(pruefeFeld(feld)){
            return this.brett[feld]=true;
        }
        return false;
    }

    public boolean[][] toArray(boolean[] arr){
        boolean[][] erg=new boolean[this.size][this.size];
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                erg[i][j]=arr[i*this.size+j];
            }
        }
        return erg;
    }

    public static void main(String[] args) {
        long start=System.nanoTime();
        Schachbrett brett=new Schachbrett(12);
        brett.solve();
        /*for(boolean[] er:brett.erg) {
            for (int zeile = 0; zeile < brett.size; zeile++) {
                for (int spalte = 0; spalte < brett.size; spalte++) {
                    if(er[zeile * brett.size + spalte]){
                        System.out.print("X  ");
                    }else {
                        System.out.print("O  ");
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }*/
        long end=System.nanoTime();
        System.out.println((end-start)/1000000.0+"ms");
        System.out.println(brett.erg.size());
    }
}
