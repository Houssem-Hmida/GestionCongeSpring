package tn.esprit.gestionconges.responce;

import java.util.List;

import tn.esprit.gestionconges.entites.Reclamation;

public class ReclamationResponce {
	private List<Reclamation> Reclamations;
	private double totalPages;
	public List<Reclamation> getReclamations() {
		return Reclamations;
	}
	public void setReclamations(List<Reclamation> reclamations) {
		Reclamations = reclamations;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public ReclamationResponce(List<Reclamation> reclamations, double totalPages) {
		super();
		Reclamations = reclamations;
		this.totalPages = totalPages;
	}
	public ReclamationResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
