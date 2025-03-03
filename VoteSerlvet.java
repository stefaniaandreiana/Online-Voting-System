package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        int partyId = Integer.parseInt(request.getParameter("party_id"));

        try (Connection conn = DBConnection.getConnection()) {
            String checkVoteSql = "SELECT * FROM votes WHERE user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkVoteSql);
            checkStmt.setInt(1, userId);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                response.sendRedirect("vote.jsp?error=You have already voted");
                return;
            }

            String voteSql = "INSERT INTO votes (user_id, party_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(voteSql);
            stmt.setInt(1, userId);
            stmt.setInt(2, partyId);
            stmt.executeUpdate();

            response.sendRedirect("results.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("vote.jsp?error=An error occurred during voting");
        }
    }
}


