package br.montreal.application.model.enuns;

public enum TipoPessoa {
    FISICA("PF"),
    JURIDICA("PJ");

    private final String value;

    TipoPessoa(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoPessoa fromValue(String v) {
        for (TipoPessoa c: TipoPessoa.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
