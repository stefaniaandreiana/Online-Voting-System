package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT p.name, COUNT(v.vote_id) AS voteCount FROM parties p LEFT JOIN votes v ON p.party_id = v.party_id GROUP BY p.party_id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<PartydResult> partyResults = new ArrayList<>();
            while (rs.next()) {
                String partyName = rs.getString("name");
                int voteCount = rs.getInt("voteCount");
                partyResults.add(new PartydResult(partyName, voteCount));
            }
            request.setAttribute("partyResults", partyResults);
            request.getRequestDispatcher("results.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
