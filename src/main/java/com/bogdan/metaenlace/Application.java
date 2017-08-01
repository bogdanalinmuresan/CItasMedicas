package com.bogdan.metaenlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.hibernate.Session;

import com.bogdan.Model.Cita;
import com.bogdan.Model.Diagnostico;
import com.bogdan.Model.Medico;
import com.bogdan.Model.Paciente;
import com.bogdan.dao.CitaDAO;
import com.bogdan.dao.CitaDAOImplHibernate;
import com.bogdan.dao.DiagnosticoDAO;
import com.bogdan.dao.DiagnosticoDAOImplHibernate;
import com.bogdan.dao.MedicoDAO;
import com.bogdan.dao.MedicoDAOImplHibernate;
import com.bogdan.dao.PacienteDAO;
import com.bogdan.dao.PacienteDAOImplHibernate;
import com.bogdan.persistence.HibernateUtil;

public class Application extends AbstractHandler {
    private static final int DEFAULT_PORT = 5000;
    private static final int PAGE_SIZE = 3000;
    private static final String INDEX_HTML = loadIndex();

    private static String loadIndex() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Application.class.getResourceAsStream("/index.html")))) {
            final StringBuilder page = new StringBuilder(PAGE_SIZE);
            String line = null;

            while ((line = reader.readLine()) != null) {
                page.append(line);
            }

            return page.toString();
        } catch (final Exception exception) {
            return getStackTrace(exception);
        }
    }

    private static String getStackTrace(final Throwable throwable) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);

        return stringWriter.getBuffer().toString();
    }

    private static int getPort() {
        String port = System.getenv().get("PORT");
        return port == null ? DEFAULT_PORT : Integer.parseInt(port);
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println(INDEX_HTML);
    }

    public static void main(String[] args) throws Exception {
       
        
    	System.out.println("Inicio del programa");

        PacienteDAO pacienteDao=new PacienteDAOImplHibernate();
        MedicoDAO medicoDao= new MedicoDAOImplHibernate();
         
        Paciente paciente1=pacienteDao.create();
        paciente1.setApellidos("martinez");
		paciente1.setClave("11115");//20
		paciente1.setDireccion("marquez de corvera");
		paciente1.setNombre("jose");
		paciente1.setNss("111111111115");//12
		paciente1.setNumtarjeta("1111111115");//30
		paciente1.setTelefono("629584745");
		paciente1.setUsuario("jo1e591");
		
		
		Medico medico1=medicoDao.create();
		medico1.setNumcolegiado("1116");//20
		medico1.setUsuario("pepe31edico");//20
		medico1.setClave("1115");//20
		medico1.setNombre("pepe");
		medico1.setApellidos("fernandez");
         
        pacienteDao.saveOrUpdate(paciente1);

         
        //se asigna el paciente al medico
        medico1.addPaciente(paciente1);
         
        //se persiste el medico
        medicoDao.saveOrUpdate(medico1);
        
        //se crea la cita
        CitaDAO citaDao=new CitaDAOImplHibernate();
        Cita cita =citaDao.create();
        cita.setMotivoCita("tiene dolores de cabeza");
        cita.setFechahora(new Date());
         
        //se crea el diagnostico
        DiagnosticoDAO diagnosticoDao=new DiagnosticoDAOImplHibernate();
        Diagnostico diagnostico=diagnosticoDao.create();
        diagnostico.setEnfermedad("catarro");
        diagnostico.setValoracionEspecialista("dolores de cabeza y de garganta");
         
        
        //se asigna el diagnostico a las cita y viceversa
        cita.setDiagnostico(diagnostico);
        diagnostico.setCita(cita);
       
         
        //relacionamos la cita con el paciente y el medico
        cita.setPaciente(paciente1);
        cita.setMedico(medico1);

        //persistimos la cita
        citaDao.saveOrUpdate(cita);
         
        //se relaciona la cita con el paciente y el medico
        paciente1.addCita(cita);
        medico1.addCita(cita);
        
        pacienteDao.saveOrUpdate(paciente1);
        medicoDao.saveOrUpdate(medico1);
        
         System.out.println("Hecho");
    }
}
