package br.montreal.application.model.enuns;

public enum MensagensSistema {

    REGISTRO_NAO_ENCONTRADO("Registro não Encontrado"),
    REALIZADO_COM_SUCESSO("Realizado com Sucesso");

    private final String value;

    MensagensSistema(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MensagensSistema fromValue(String v) {
        for (MensagensSistema c: MensagensSistema.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
