
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class consulta extends HttpServlet {
    // Usa um prepared Statement para registrar alunos no banco

    private PreparedStatement pNome, pSobre, pEmail;

    /**
     * Utiliza o método init do Servlet para iniciar conexão com o banco
     */
    public void init() throws ServletException {
        inicializaJdbc();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s = request.getParameter("Options");
        String valor = request.getParameter("nome");
        init();
        try {
            valor = "%" + valor + "%";
            if(s.compareTo("nome") == 0) {
                pNome.setString(1, valor);
                ResultSet rs = pNome.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("codigo");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    out.println("<b>Codigo = </b>" + id + "<p>");
                    out.println("<b>Nome = </b>" + nome + "<p>");
                    out.println("<b>Sobrenome = </b>" + sobrenome + "<p>");
                    out.println("<b>E-mail = </b>" + email + "<p>");
                    out.println("----------------------------------<p>");
                }
            }
            else if(s.compareTo("sobrenome") == 0) {
                pSobre.setString(1, valor);
                ResultSet rs = pSobre.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("codigo");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    out.println("<b>Codigo = </b>" + id + "<p>");
                    out.println("<b>Nome = </b>" + nome + "<p>");
                    out.println("<b>Sobrenome = </b>" + sobrenome + "<p>");
                    out.println("<b>E-mail = </b>" + email + "<p>");
                    out.println("----------------------------------<p>");
                }
            }
            else {
               pEmail.setString(1, valor);
               ResultSet rs = pEmail.executeQuery();
               while(rs.next()) {
                    int id = rs.getInt("codigo");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    out.println("<b>Codigo = </b>" + id + "<p>");
                    out.println("<b>Nome = </b>" + nome + "<p>");
                    out.println("<b>Sobrenome = </b>" + sobrenome + "<p>");
                    out.println("<b>E-mail = </b>" + email + "<p>");
                    out.println("----------------------------------<p>");
               }
            }
            
            
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            return;
        }
    }
   
    
    private void inicializaJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/escola", "root", "VcTr100");
            pNome = c.prepareStatement("select * from aluno where nome like ?");
            pSobre = c.prepareStatement("select * from aluno where sobrenome like ?");
            pEmail = c.prepareStatement("select * from aluno where email like ?");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
