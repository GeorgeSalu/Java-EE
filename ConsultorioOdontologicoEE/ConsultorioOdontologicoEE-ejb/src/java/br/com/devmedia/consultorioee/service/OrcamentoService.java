/*
 * Copyright (C) 2014 dyego.carmo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.service.repository.OrcamentoRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dyego.carmo
 */
@Stateless
@LocalBean
public class OrcamentoService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private OrcamentoRepository orcamentoRepository;
    
    
    @PostActivate
    @PostConstruct
    private void postConstruct() {
        orcamentoRepository =  new OrcamentoRepository(em);
    }
    
    public Orcamento addOrcamento(Orcamento orc) {
        return orcamentoRepository.addOrcamento(orc);
    }
    
    public Orcamento setOrcamento(Orcamento orc) {
        return orcamentoRepository.setOrcamento(orc);
    }
    
    public Orcamento getOrcamento(Integer idOfOrcamento) {
        return orcamentoRepository.getOrcamento(idOfOrcamento);
    }
    
    public void removeOrcamento(Orcamento orc) {
        orcamentoRepository.removeOrcamento(orc);
    }
    
    public Orcamentoitem addItem(Orcamentoitem item) {
        return orcamentoRepository.addItem(item);
    }
    
    public Orcamentoitem setItem(Orcamentoitem item) {
        return orcamentoRepository.setItem(item);
    }
    
    public Orcamentoitem getItem(Integer idOfItem) {
        return orcamentoRepository.getItem(idOfItem);
    }
    
    public List<Orcamento> getOrcamentos(Integer idofCustomer) {
        return orcamentoRepository.getOrcamentos(idofCustomer);
    }
    
    public List<Orcamentoitem> getItens(Integer idOfOrcamento) {
        return orcamentoRepository.getItens(idOfOrcamento);
    }
    
}
