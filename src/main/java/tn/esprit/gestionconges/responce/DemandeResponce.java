package tn.esprit.gestionconges.responce;

import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Reclamation;

import java.util.List;

public class DemandeResponce {
    private List<DemandeCongee> demandeCongees;
    private double totalPages;

    public List<DemandeCongee> getDemandeCongees() {
        return demandeCongees;
    }

    public void setdemandeCongees(List<DemandeCongee> demandeCongees) {
        demandeCongees = demandeCongees;
    }

    public double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(double totalPages) {
        this.totalPages = totalPages;
    }

    public DemandeResponce(List<DemandeCongee> demandeCongees, double totalPages) {
        this.demandeCongees = demandeCongees;
        this.totalPages = totalPages;
    }

    public DemandeResponce() {
    }
}
