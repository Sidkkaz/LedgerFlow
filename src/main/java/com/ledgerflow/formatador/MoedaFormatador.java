package com.ledgerflow.formatador;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaFormatador {

    public static String Moeda(double valor) {
        return NumberFormat.getCurrencyInstance(Locale.of("pt","BR")).format(valor);
    }
}
