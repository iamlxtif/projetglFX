package users;

import documents.Bilan;
import documents.Plan;
import documents.SauvegardeMonsuel;

import java.sql.Connection;
import java.sql.SQLException;

public class Directeur extends Utilisateur{
    public Directeur(String nomUtilisateur, String motDePasse){
        super(nomUtilisateur,motDePasse);
    }
    public void etablirPlan(Connection con, double CA) throws SQLException {
        Plan plan = new Plan(CA);
        plan.insertPlan(con);
    }
    public void editPlan(Connection con, Plan plan, double CA ) throws SQLException {
        plan.setCA(CA);
        plan.updatePlan( con );
    }
    public Bilan consulterBilan( Connection con) throws SQLException {
        Bilan bilan = new Bilan();
        bilan.setMonthlyStat( con );
        return bilan;
    }
    public SauvegardeMonsuel creerSauvegardeMonsuel(Connection con) throws SQLException {
        return new SauvegardeMonsuel(con);
    }
}
