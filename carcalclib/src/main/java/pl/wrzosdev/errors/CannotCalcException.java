package pl.wrzosdev.errors;

/**
 * Błąd obliczenia - do obsłużenia w targecie
 * todo niekardynalny exception
 * todo sprawdzić czy te konstruktory są niezbędne - bo wątpie
 */
public class CannotCalcException extends IllegalStateException {

    private CalcErrorCode calcErrorCode;

    public CannotCalcException() {
        super();
    }

    public CannotCalcException(String s, CalcErrorCode calcErrorCode) {
        super(s);

        this.calcErrorCode = calcErrorCode;
    }

    public CannotCalcException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CannotCalcException(Throwable throwable) {
        super(throwable);
    }

    public CalcErrorCode getCalcErrorCode() {
        return calcErrorCode;
    }
}
