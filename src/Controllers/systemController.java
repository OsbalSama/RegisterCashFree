/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.SystemConfigurations;
import Models.Messages;
import Views.adminControlls.appSettings;
import Views.commondialogs.logFrame;
import com.google.gson.Gson;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ARTEMISA
 */
public class systemController {

    String Class = "systemController";

    String mainRepos = "source\\";
    String Resources = "resources\\";

    String AppName = "Register Cash Free";
    String Version = "v9.0";

    String companyName = "Poverbius Software";
    String mainDev = "Osbaldo Toledo Ramos - Freelancer Dev.";
    String mainEmail = "proverbius.sw@gmail.com";
    String closingPhrase = "Frase de la semana";

    String homePage = "https://proverbius-sw.netlify.app/";
    String appPage = "https://proverbius-sw.netlify.app/register_cash_free";
    String Contact = "https://proverbius-sw.netlify.app/contacto.html";

    String archivo = "source\\DBS_0001.dat";

    public boolean existFile() {
        return new File(archivo).exists();
    }

    SystemConfigurations SystemConfig;
    dataController Reset = dataController.getInstance();

    Gson g = new Gson();

    public void resetSystemData() {
        try {
            SystemConfig = new SystemConfigurations();
            SystemConfig.setClient("Free Account");
            SystemConfig.setSerial("FreeSoftware");
            SystemConfig.setActivated(true);

            SystemConfig.setStorename("DEFAULT NAME STORE");
            SystemConfig.setNorfc(true);
            SystemConfig.setAdress("DEFAULT ADRESS");
            SystemConfig.setNophone(true);

            SystemConfig.setPapersize(0);
            SystemConfig.setTerms("DEFAULT TERMS");
            SystemConfig.setReset(true);

            SystemConfig.setUpCash(true);
            SystemConfig.setInCash(0);

            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();

            updateSystemConfigFile();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetDataBase()", e);
        }
    }

    //Get & Set
    public String getArchivo() {
        return archivo;
    }

    public String getResources() {
        return Resources;
    }

    public void setResources(String Resources) {
        this.Resources = Resources;
    }

    public SystemConfigurations getSystemConfig() {
        getConfigSettingsFile();
        return SystemConfig;
    }

    public String getMainRepos() {
        return mainRepos;
    }

    public void setMainRepos(String mainRepos) {
        this.mainRepos = mainRepos;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMainDev() {
        return mainDev;
    }

    public void setMainDev(String mainDev) {
        this.mainDev = mainDev;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public String getPopularPhrase() {
        randomPhrase();
        return closingPhrase;
    }

    public void setClosingPhrase(String closingPhrase) {
        this.closingPhrase = closingPhrase;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getAppPage() {
        return appPage;
    }

    public String getContact() {
        return Contact;
    }

    //Instance configurations
    private static systemController instance;

    public static systemController getInstance() {
        if (instance == null) {
            instance = new systemController();
        }
        return instance;
    }

    public systemController() {
        randomPhrase();
        getConfigSettingsFile();
    }

    public boolean startSystem() {
        boolean resp = false;
        try {
            if (SystemConfig.isReset()) {
                if (Reset.resetAllApp()) {
                    logFrame cs = new logFrame();
                    cs.setData();
                    cs.show();
                    resp = true;
                }
            } else {
                logFrame cs = new logFrame();
                cs.setData();
                cs.show();
                resp = true;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "startSystem()", e);
        }
        return resp;
    }

    //TOOLS AND UTILITIES
    public void SystemBackup() {
        try {
            JFileChooser savefile = new JFileChooser();
            savefile.setDialogTitle("Respaldo del Sistema");
            FileFilter filter = new FileNameExtensionFilter("Archivo ZIP", "zip");
            savefile.addChoosableFileFilter(filter);
            savefile.setAcceptAllFileFilterUsed(false);
            savefile.setSelectedFile(new File("RC-" + new SimpleDateFormat("ddMMYYHHmmss").format(new Date()) + "_Backup.zip"));
            int sf = savefile.showSaveDialog(null);
            if (sf == JFileChooser.APPROVE_OPTION) {
                if (compressFiles(savefile.getSelectedFile().toString(), savefile.getSelectedFile().getName())) {
                    new Messages().closeMessage("Proceso Terminado", "Respaldo realizado...");
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "SystemBackup()", e);
        }
    }

    public boolean compressFiles(String ruta, String nombreArchivo) {
        boolean resp = false;
        try {
            ZipFile(getMainRepos(), ruta);
            resp = true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "compressFiles()", e);
        }
        return resp;
    }

    public void ZipFile(String archivo, String archivoZIP) throws Exception {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(archivoZIP));
        addDirectory("", archivo, zip);
        zip.flush();
        zip.close();
    }

    public void addDirectory(String ruta, String carpeta, ZipOutputStream zip) throws Exception {
        File directorio = new File(carpeta);
        for (String nombreArchivo : directorio.list()) {
            if (ruta.equals("")) {
                addFile(directorio.getName(), carpeta + "/" + nombreArchivo, zip);
            } else {
                addFile(ruta + "/" + directorio.getName(), carpeta + "/" + nombreArchivo, zip);
            }
        }
    }

    public void addFile(String ruta, String directorio, ZipOutputStream zip) throws Exception {
        File archivo = new File(directorio);
        if (archivo.isDirectory()) {
            addDirectory(ruta, directorio, zip);
        } else {
            byte[] buffer = new byte[4096];
            int leido;
            FileInputStream entrada = new FileInputStream(archivo);
            zip.putNextEntry(new ZipEntry(ruta + "/" + archivo.getName()));
            while ((leido = entrada.read(buffer)) > 0) {
                zip.write(buffer, 0, leido);
            }
        }
    }

    //ICONS AND IMAGES FROM SYSTEM
    public void resetLogo() {
        try {
            Path origen = FileSystems.getDefault().getPath(Resources + "Logo.png");
            File f = new File(Resources + "LogoCOPY.png");
            File file2 = new File(Resources + "LOGO.png");
            file2.createNewFile();
            f.renameTo(file2);
            Path destino = FileSystems.getDefault().getPath(f.getAbsolutePath());
            Files.copy(destino, origen, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetLogo()", e);
        }
    }

    public ImageIcon getLoadingImage(int Width, int Height) {
        String ruta = Resources + "main.png";
        ImageIcon icon = new ImageIcon(new File(ruta).getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getUserImage(int Width, int Height) {
        String ruta = Resources + "userIcon.png";
        ImageIcon icon = new ImageIcon(new File(ruta).getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getmenuIcon(int Width, int Height) {
        String ruta = Resources + "menuIcon.png";
        ImageIcon icon = new ImageIcon(new File(ruta).getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getImageIcon() {
        ImageIcon resp = null;
        try {
            File f = new File(Resources + "appPNG.png");
            resp = new ImageIcon(f.getAbsolutePath());
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getImageIcon()", e);
        }
        return resp;
    }

    //PRODUCT INFORMATION
    public void randomPhrase() {
        List<String> fraces = new ArrayList();
        fraces.add("<html><center>Prepare for Unforeseen Consequences<br>-GMan, Half Life 2");
        fraces.add("<html><center>GET TO THE CHOPPA!<br>-Alan \"Dutch\" Schaefer");
        fraces.add("<html><center>Sólo hay una persona que puede decidir lo que voy a hacer, y soy yo mismo<br>-Ciudado Kane, 1941");
        fraces.add("<html><center>Sólo los soñadores mueven montañas<br>-Fitzcarraldo, 1982");
        fraces.add("<html><center>Sigue nadando<br>-Buscando a Nemo, 2003");
        fraces.add("<html><center>Sólo tú puedes decidir qué hacer con el tiempo que se te ha dado<br>-El señor de los anilllos: La comunidad del anillo, 2001");
        fraces.add("<html><center>Todos morimos, lo que importa es el cómo y el cuándo<br>-Braveheart, 1995");
        fraces.add("<html><center>¿Por qué caemos, Bruce? Para aprender a levantarnos<br>-Batman Begins, 2005");
        fraces.add("<html><center>Yo sólo puedo mostrarte la puerta, tú tienes que atravesarla<br>-Matrix, 1999");
        fraces.add("<html><center>Las grandes cosas tienen pequeños comienzos<br>-Prometheus, 2012");
        fraces.add("<html><center>Joven fui, y he envejecido, Y no he visto justo desamparado, Ni su descendencia que mendigue pan.<br>-Salmos 37:25 RVR1960");
        fraces.add("<html><center>Estén siempre alegres, oren sin cesar, den gracias a Dios en toda situación, porque esta es su voluntad para ustedes en Cristo Jesús.<br>-1 Tesalonicenses 5:16-18");
        fraces.add("<html><center>Crean que ya han recibido todo lo que estén pidiendo en oración, y lo obtendrán.<br>-Marcos 11:24");
        fraces.add("<html><center>Hagan todo con amor.<br>-1 Corintios 16:14");
        fraces.add("<html><center>Pon en manos del Señor todas tus obras, y tus proyectos se cumplirán.<br>-Proverbios 16:3");
        fraces.add("<html><center>Pues conozco los planes que para ustedes tengo, dice el Señor. Son planes de bien y no de mal, para darles un futuro y una esperanza.<br>-Jeremías 29:11");
        fraces.add("<html><center>Hagan lo que hagan, trabajen de buena gana, como para el Señor, conscientes de que el Señor los recompensará con la herencia.<br>-Colosenses 3:23-24");
        fraces.add("<html><center>Siempre humildes y amables, pacientes, tolerantes unos con otros en amor.<br>-Efesios 4:2");
        fraces.add("<html><center>Ahora bien, la fe es la garantía de lo que se espera, la certeza de lo que no se ve.<br>-Hebreos 11:1");
        fraces.add("<html><center>Confía en el Señor de todo corazón, y no en tu propia inteligencia. Reconócelo en todos tus caminos, y él allanará tus sendas.<br>-Proverbios 3:5-6");

        setClosingPhrase(fraces.get(getRandomNumber(0, fraces.size())));
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //WEBSITES and Settings
    public void updateStoreData() {
        appSettings as = new appSettings(null, true);
        as.selectupdateData();
        as.show();
    }

    public void editTicketSettings() {
        appSettings as = new appSettings(null, true);
        as.selectTicketSettings();
        as.show();
    }

    public void infoProd() {
        appSettings as = new appSettings(null, true);
        as.selectProductInfo();
        as.show();
    }

//    public void getWebsite() {
//        Desktop d = Desktop.getDesktop();
//        try {
//            d.browse(new URI(getHomePage()));
//        } catch (Exception e) {
//            new Messages().errorMessage(Class, "getWebsite()", e);
//        }
//    }
//    public void getHelp() {
//        Desktop d = Desktop.getDesktop();
//        try {
//            d.browse(new URI(getHomePage()));
//        } catch (Exception e) {
//            new Messages().errorMessage(Class, "getHelp()", e);
//        }
//    }
    public void gethomePage() {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(getHomePage()));
        } catch (Exception e) {
            new Messages().errorMessage(Class, "gethomePage()", e);
        }
    }

    public void getappPage() {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(getAppPage()));
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getappPage()", e);
        }
    }

    public void getContactPage() {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(getContact()));
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getContactPage()", e);
        }
    }

    public void enableResetMode() {
        try {
            String text = "<html>"
                    + "<center>"
                    + "<b>"
                    + "¡SE PERDERAN TODOS LOS DATOS GUARDADOS EN EL SISTEMA!"
                    + "</b>"
                    + "<br>"
                    + "<br>"
                    + "¿Desea continuar con el proceso?"
                    + "</center>"
                    + "</html>";
            if (new Messages().confirmMessage("Modo de Fabrica", text)) {
                SystemConfig.setReset(true);
                updateSystemConfigFile();
                sessionController.getInstance().exitFromReset();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSystemConfigs()", e);
        }
    }

    public void disableResetMode() {
        try {
            SystemConfig.setReset(false);
            updateSystemConfigFile();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSystemConfigs()", e);
        }
    }

    public void createSystemConfigs() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            createSystemConfig();
            getConfigSettingsFile();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSystemConfigs()", e);
        }
    }

    public boolean createSystemConfig() {
        boolean resp = false;
        try {
            SystemConfigurations settings = new SystemConfigurations();
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            bw.write(new cypherController().EncryptData(g.toJson(settings)));
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSystemConfig()", e);
        }
        return resp;
    }

    public void getConfigSettingsFile() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    SystemConfig = g.fromJson(ln, SystemConfigurations.class);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getConfigSettingsFile()", e);
        }
    }

    public void updateSystemConfigFile() {
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            bw.write(new cypherController().EncryptData(g.toJson(SystemConfig)));
            bw.newLine();
            bw.close();
            getConfigSettingsFile();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSystemConfigFile()", e);
        }
    }

    public void updateStorename(String newName) {
        this.SystemConfig.setStorename(newName);
        updateSystemConfigFile();
    }

    public void updateNoRFC(boolean resp) {
        this.SystemConfig.setNorfc(resp);
        updateSystemConfigFile();
    }

    public void updateRFC(String newRFC) {
        this.SystemConfig.setRfc(newRFC);
        updateSystemConfigFile();
    }

    public void updateAdress(String newAdress) {
        this.SystemConfig.setAdress(newAdress);
        updateSystemConfigFile();
    }

    public void updateNoPhone(boolean resp) {
        this.SystemConfig.setNophone(resp);
        updateSystemConfigFile();
    }

    public void updatePhone(String newPhone) {
        this.SystemConfig.setPhone(newPhone);
        updateSystemConfigFile();
    }

    public void updatePrinter(String newPrinter) {
        this.SystemConfig.setPrinter(newPrinter);
        updateSystemConfigFile();
    }

    public void updatePaperSize(int newPaperSize) {
        this.SystemConfig.setPapersize(newPaperSize);
        updateSystemConfigFile();
    }

    public int getPaperSize() {
        return this.SystemConfig.getPapersize();
    }

    public String getSaleTerms() {
        return this.SystemConfig.getTerms();
    }

    public void updateTerms(String newTerms) {
        this.SystemConfig.setTerms(newTerms);
        updateSystemConfigFile();
    }

    public String getStoreName() {
        return this.SystemConfig.getStorename();
    }

    public String getRFC() {
        return this.SystemConfig.getRfc();
    }

    public String getPhone() {
        return this.SystemConfig.getPhone();
    }

    public String getAdress() {
        return this.SystemConfig.getAdress();
    }

    public boolean isUpCash() {
        return this.SystemConfig.isUpCash();
    }

    public void enableUpCash() {
        this.SystemConfig.setUpCash(true);
        updateSystemConfigFile();
    }

    public void disableUpCash() {
        this.SystemConfig.setUpCash(false);
        updateSystemConfigFile();
    }

    public double getInCash() {
        return this.SystemConfig.getInCash();
    }

    public void setInCash(double inCash) {
        this.SystemConfig.setInCash(inCash);
    }
}
