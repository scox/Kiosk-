package com.kiosk.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jmesa.model.TableModel;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.editor.NumberCellEditor;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kiosk.dao.db.AdminDBDao;
import com.kiosk.dao.db.LoginDBDao;
import com.kiosk.model.Transaction;
import com.kiosk.model.User;

/**
 * Author: Sam Cox Date: 06/01/2012 AddAudioController.Java: This class provides the presentation layer for 
 * admin requests made by the client
 */

@Controller
@SessionAttributes
public class AdminController {

	@Autowired
	private AdminDBDao adminDBDao;
	@Autowired
	private LoginDBDao loginDBDao;

	@RequestMapping("/adminLogin.htm")
	@ModelAttribute
	public void getAdminLogin(ModelMap model, HttpServletRequest request) {

		// Safely end the session and log user out
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getSession().invalidate();
		}

	}

	// login administrator and direct to homepage
	@RequestMapping("/admin.htm")
	@ModelAttribute
	public ModelAndView getAdminLogin(ModelMap model,
			HttpServletRequest request,
			@RequestParam(value = "type") String type) {

		ModelAndView mv = new ModelAndView();
		if (type.equalsIgnoreCase("login")) {

			String password = request.getParameter("password");
			String username = request.getParameter("username");
			User user = loginDBDao.getAuthenticateUser(username, password);

			// if user exists set up a session and set the user for the session
			if (user != null) {
				HttpSession session = request.getSession(true);
				mv.addObject("user", user);
				session.setAttribute("user", user);
				type = "home";
				mv.setViewName("admin");

			}

			else {
				// user does not exist
				mv.setViewName("error");

			}
		}

		else {

			// return homepage if session exists else direct to login

			HttpSession session = request.getSession(false);
			if (session != null) {
				mv.setViewName("admin");
			} else {
				mv.setViewName("adminLogin");
			}

		}

		mv.addObject("type", type);
		return mv;

	}

	// get all transactions and present to the administrator

	@RequestMapping("/transactions.htm")
	@ModelAttribute
	public ModelAndView getTransactions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		ModelAndView mv = new ModelAndView();
		List<Transaction> trans = adminDBDao.getTransactions();

		TableModel tableModel = new TableModel("Transactions", request,
				response);
		tableModel.setItems(trans);
		tableModel.setStateAttr("restore");

		// Web Option
		tableModel.setTable(getTable());
		String html = tableModel.render();
		request.setAttribute("output", html);
		mv.addObject("size", trans.size());
		mv.setViewName("transactions");
		return mv;

	}

	// Set up the web view using jmesa table rendering API

	private Table getTable() {

		HtmlTable htmlTable = new HtmlTable();
		HtmlRow htmlRow = new HtmlRow();
		htmlTable.setRow(htmlRow);
		htmlRow.addColumn(buildColumn("transID", "Transaction ID",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("name", "Customer Name",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("email", "Customer Email",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("pin", "Pin", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("address", "Address", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("postCode", "Post Code",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("telNo", "Phone No", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("language", "Language", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("level", "Level", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("price", "Price", ColumnType.NUMBER));
		htmlRow.addColumn(buildColumn("paymentStatus", "Payment Status",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("createdOn", "Date Created",
				ColumnType.DATE));
		htmlRow.addColumn(buildColumn("customerType", "Customer Type",
				ColumnType.NORMAL));

		return htmlTable;
	}

	private Column buildColumn(String var, String name, ColumnType type) {
		Column column = new HtmlColumn(var).title(name);
		return decorateColumn(column, type);
	}

	private Column decorateColumn(Column column, ColumnType type) {
		CellEditor ce = null;
		switch (type) {
		case NUMBER:
			ce = new NumberCellEditor("###,##0.00");
			break;

		case DATE:
			ce = new DateCellEditor("dd/MMM/yyyy");
		default:

		}

		if (ce != null) {
			column.cellEditor(ce);
		}

		return column;
	}

	private enum ColumnType {
		NUMBER, DATE, NORMAL;
	}

}
