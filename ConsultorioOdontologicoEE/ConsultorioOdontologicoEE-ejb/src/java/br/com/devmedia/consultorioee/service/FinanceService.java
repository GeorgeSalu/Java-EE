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

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Parcela;
import br.com.devmedia.consultorioee.service.repository.FinanceRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author dyego.carmo
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FinanceService extends BasicService {

private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private FinanceRepository financeRepository;

    @PostActivate
    @PostConstruct
    private void postConstruct() {
        financeRepository = new FinanceRepository(em);
    }

    public Parcela addParcela(Parcela par) {
        return financeRepository.addParcela(par);
    }

    public Parcela getParcela(Integer idOfParcela) {
        return financeRepository.getParcela(idOfParcela);
    }

    public Parcela setParcela(Parcela par) {
        return financeRepository.setParcela(par);
    }

    public void removeParcela(Parcela par) {
        financeRepository.removeParcela(par);
    }

    public List<Parcela> getParcelasByOrcamento(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamento(orcamentoId);
    }

    public List<Parcela> getParcelasOfOrcamentoPagas(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamentoPagas(orcamentoId);
    }

    public List<Parcela> getParcelasOfOrcamentoEmAberto(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamentoEmAberto(orcamentoId);
    }

    public List<Parcela> getParcelasOfCustomer(int customerId) {
        return financeRepository.getParcelasOfCustomer(customerId);
    }

    public List<Parcela> getParcelasOfCustomerPagas(int orcamentoId) {
        return financeRepository.getParcelasOfCustomerPagas(orcamentoId);
    }

    public List<Parcela> getParcelasOfCustomerEmAberto(int orcamentoId) {
        return financeRepository.getParcelasOfCustomerEmAberto(orcamentoId);
    }

    public Parcela setPagamentoParcela(int idOfParcela) {
        return financeRepository.setPagamentoParcela(idOfParcela);
    }

    public void parcelaOrcamento(Orcamento orcGravado) {
        float valorParcela = Math.round(orcGravado.getOrcTotal().floatValue() / orcGravado.getOrcTimes().intValue());
        for (int i = 0; i < orcGravado.getOrcTimes(); i++) {
            Parcela par = new Parcela();
            par.setParNumero(i + 1);
            par.setParOrcamento(orcGravado);
            par.setParPago(false);
            if ((i + 1) == orcGravado.getOrcTimes()) {
                float valorUltimaParcela = valorParcela * i;
                valorUltimaParcela = orcGravado.getOrcTotal().floatValue() - valorUltimaParcela;
                par.setParValue(BigDecimal.valueOf(valorUltimaParcela));

            } else {
                par.setParValue(BigDecimal.valueOf(valorParcela));
            }
            addParcela(par);
        }
    }

    public void eliminarParcelas(Orcamento orc) {
        financeRepository.eliminarParcelas(orc);
    }

    public List<Customer> getClientesComParcelaEmAberto() {
        return financeRepository.getClientesComParcelaEmAberto();
    }

    public byte[] getPDF(Parcela par) throws JRException {
        String codigoBarras ="9126731921927319287312973";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("parcela", par);
        parameters.put("codigobarras", codigoBarras);
        JasperReport jr = JasperCompileManager.compileReport(FinanceService.class.getResourceAsStream("boletoParcela.jrxml"));
    
        return null;
    }    
}
