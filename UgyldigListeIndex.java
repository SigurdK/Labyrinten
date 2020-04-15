public class UgyldigListeIndex extends RuntimeException{
    UgyldigListeIndex(int indeks) {
        super("Ugyldig indeks:"+indeks);
    }
}
