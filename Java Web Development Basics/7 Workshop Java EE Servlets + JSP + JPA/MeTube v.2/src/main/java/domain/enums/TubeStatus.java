package domain.enums;

public enum TubeStatus {
    PENDING(0), APPROVED(1), DECLINED(2);

    private int value;

    TubeStatus(int value) {
        this.value = value;
    }

    public static int valueOf(TubeStatus status) {
        return status.getValue();
    }

    public int getValue() {
        return value;
    }
}
