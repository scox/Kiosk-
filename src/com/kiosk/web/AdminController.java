package com.kiosk.web;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.kiosk.model.Transaction;
import com.kiosk.model.User;
import com.kiosk.service.AdminService;
import com.kiosk.service.LoginService;

@Controller
@SessionAttributes
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginService loginService;

	public static User user;

	@RequestMapping("/adminLogin.htm")
	@ModelAttribute
	public void getAdminLogin(ModelMap model) {

		user = null;

	}

	@RequestMapping("/admin.htm")
	@ModelAttribute
	public ModelAndView getAdminLogin(ModelMap model,
			HttpServletRequest request,
			@RequestParam(value = "type") String type) {

		ModelAndView mv = new ModelAndView();
		if (type.equalsIgnoreCase("login")) {

			String password = request.getParameter("password");
			String username = request.getParameter("username");
			user = loginService.getAuthenticateUser(username, password);

			if (user != null) {
				mv.addObject("user", user);
				type = "home";
				mv.setViewName("admin");

			}

			else {

				mv.setViewName("error");

			}
		}

		else {
			mv.setViewName("admin");

		}

		System.out.println(type);
		mv.addObject("type", type);
		return mv;

	}

	@RequestMapping("/transactions.htm")
	@ModelAttribute
	public ModelAndView getTransactions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		ModelAndView mv = new ModelAndView();

		List<Transaction> trans = adminService.getTransactions();

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

	// Set up web view

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
		htmlRow.addColumn(buildColumn("price", "Price",
				ColumnType.NUMBER));
		htmlRow.addColumn(buildColumn("paymentStatus", "Payment Status",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("createdOn", "Date Created",
				ColumnType.DATE));

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
