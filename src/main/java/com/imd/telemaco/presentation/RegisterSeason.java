package com.imd.telemaco.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.ValidateSeasonServices;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.business.exception.SeasonIncompleteException;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Series;
import com.imd.telemaco.entity.User;

/**
 * Servlet implementation class ResgisterSeason
 */
@WebServlet("/ResgisterSeason")
public class RegisterSeason extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.getAttribute("logged");

        try {
            Season season = new Season();

            String number = request.getParameter("number");
            Series serie = (Series) session.getAttribute("serie");
            int numberInt = Integer.parseInt(number);
            season = new Season(numberInt, serie.getId());

            ValidateSeasonServices validate = new ValidateSeasonServices();
            validate.validSeasonInsert(season);

            SerieDAO dao = SerieDAO.getInstance();
            Series updateSerie = dao.select(serie.getId());
            session.setAttribute("serie", updateSerie);

            response.sendRedirect("Serie.jsp");
            // TODO fazer mensagem de cadastrado com sucesso

        } catch (SeasonExistsException | SeasonIncompleteException | DatabaseException | CloseConnectionException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
            new RuntimeException(e); //FIXME
        } finally {
            out.close();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
