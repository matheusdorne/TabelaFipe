package br.com.TabelaFipe.model;

import br.com.TabelaFipe.model.Dados;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Anos(List<Dados> anos) {
}
