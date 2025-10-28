package com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ValorMensalidade {
    private int id;
    private BigDecimal valor;
    private boolean manualFlag = false;
    private boolean desconto = false;
    private LocalDateTime dataInclusao;

    public ValorMensalidade() {}

    public ValorMensalidade(int id, BigDecimal valor, boolean manualFlag, boolean desconto) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0.0) {
            throw new IllegalArgumentException("Valor da mensalidade inválido!");
        }

        this.id = id;
        this.valor = valor;
        this.manualFlag = manualFlag;
        this.desconto = desconto;
        this.dataInclusao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isManualFlag() {
        return manualFlag;
    }

    public void setManualFlag(boolean manualFlag) {
        this.manualFlag = manualFlag;
    }

    public boolean isDesconto() {
        return desconto;
    }

    public void setDesconto(boolean desconto) {
        this.desconto = desconto;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
