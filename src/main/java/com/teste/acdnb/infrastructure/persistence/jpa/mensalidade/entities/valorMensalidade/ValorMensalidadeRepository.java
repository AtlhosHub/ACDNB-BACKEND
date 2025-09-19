package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ValorMensalidadeRepository extends JpaRepository<ValorMensalidadeEntity, Integer> {
    List<ValorMensalidadeEntity> findByValorAndManual(BigDecimal valor, Boolean manual);

    ValorMensalidadeEntity findFirstByManualFalseAndDescontoFalseOrderByDataInclusaoDesc();
}
