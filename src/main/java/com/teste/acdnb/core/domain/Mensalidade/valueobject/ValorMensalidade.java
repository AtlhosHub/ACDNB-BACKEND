package com.teste.acdnb.core.domain.Mensalidade.valueobject;

import com.teste.acdnb.core.domain.Mensalidade.Mensalidade;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValorMensalidade {
    private String id;
    private Double value;
    private boolean manual = false;
    private boolean desconto = false;
    private LocalDateTime dataInclusao;
    private List<Mensalidade> mensalidades = new ArrayList<>();

    private ValorMensalidade(Double value, boolean manual, boolean desconto) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.manual = manual;
        this.desconto = desconto;
        this.dataInclusao = LocalDateTime.now();
    }

    public ValorMensalidade of(Double value, boolean manual, boolean desconto) {
        if(value == null || value <= 0.0) {
            throw new IllegalArgumentException("Valor da mensalidade inválido!");
        }

        return new ValorMensalidade(value, manual, desconto);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
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

    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
    }
}
