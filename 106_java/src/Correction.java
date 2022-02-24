package src;

public class Correction {
    
    public static class TempsCorr{
        private int h;
        private int m;
        private int s;

        public TempsCorr(int h, int m, int s){
            this.h = h;
            this.m = m;
            this.s = s;
        }

        public int getH(){
            return this.h;
        }

        public int getM(){
            return this.m;
        }

        public int getS(){
            return this.s;
        }

        public void setH(int h){
            this.h = h;
        }

        public void setM(int m){
            this.m = m;
        }

        public void setS(int s){
            this.s = s;
        }

        public int toSeconds(){
            return 3600 * h + 60 * m + s;
        }

        public int delta(TempsCorr t){
            return this.toSeconds() - t.toSeconds();
        }

        public boolean apres(TempsCorr t){
            return this.toSeconds() > t.toSeconds();
        }

        public void ajouter(TempsCorr t){
            int nH = (this.getH() + t.getH()) % 24;
            int nM = (this.getM() + t.getM()) % 60;
            int nS = (this.getS() + t.getS()) % 60;

            this.setH(nH);
            this.setM(nM);
            this.setS(nS);
        }

        public String toString(){
            return String.format("%02d:%02d:%02d", h, m, s);
        }
    }
    
    public static class ChansonCorr {
        private String t;
        private String a;
        private TempsCorr d;

        public ChansonCorr(String t, String a, TempsCorr d){
            this.t = t;
            this.a = a;
            this.d = d;
        }

        public String getTitre() {
            return this.t;
        }

        public String getAuteur() {
            return this.a;
        }

        public TempsCorr getDuree(){
            return this.d;
        }

        public String toString() {
            return this.t + " - " + this.a + " - " + this.d.toString();
        }
   }
}