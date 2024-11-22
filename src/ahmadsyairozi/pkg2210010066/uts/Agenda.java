/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahmadsyairozi.pkg2210010066.uts;

/**
 *
 * @author User
 */
public class Agenda {
    private int id;
    private String hari;
    private String tanggal;
    private String jam;
    private String agenda_pribadi;

    // Konstruktor
    public Agenda(int id, String hari, String tanggal, String jam, String agenda_pribadi) {
        this.id = id;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jam = jam;
        this.agenda_pribadi = agenda_pribadi;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public String getHari() { return hari; }
    public String getTanggal() { return tanggal; }
    public String getJam() { return jam; }
    public String getAgendaPribadi() { return agenda_pribadi; }  
}
