package com.ledgerflow.formatador;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaFormatador {

    public static String Moeda(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(Locale.of("pt","BR")).format(valor);
    }
}
