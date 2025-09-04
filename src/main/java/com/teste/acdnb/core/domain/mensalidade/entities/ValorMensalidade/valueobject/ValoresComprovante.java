package com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.valueobject;

import java.math.BigDecimal;

public class ValoresComprovante {
    private BigDecimal valorCheio;
    private BigDecimal valorDescontoAplicado;

    private ValoresComprovante(BigDecimal valorCheio, BigDecimal valorDescontoAplicado) {
        this.valorCheio = valorCheio;
        this.valorDescontoAplicado = valorDescontoAplicado;
    }

    public ValoresComprovante of(BigDecimal valorCheio, BigDecimal valorDescontoAplicado) {
        if(valorCheio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do comprovante não pode ser 0");
        }

        if(valorDescontoAplicado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do comprovante não pode ser 0");
        }
        
        return new ValoresComprovante(valorCheio, valorDescontoAplicado);
    }

    public BigDecimal getValorCheio() {
        return valorCheio;
    }

    public void setValorCheio(BigDecimal valorCheio) {
        this.valorCheio = valorCheio;
    }

    public BigDecimal getValorDescontoAplicado() {
        return valorDescontoAplicado;
    }

    public void setValorDescontoAplicado(BigDecimal valorDescontoAplicado) {
        this.valorDescontoAplicado = valorDescontoAplicado;
    }
}
