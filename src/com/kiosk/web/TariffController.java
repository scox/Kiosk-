package com.kiosk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.model.TableModel;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.NumberCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.editor.HtmlCellEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kiosk.dao.db.TariffDBDao;
import com.kiosk.service.TariffService;

/**
 * Author: Sam Cox Date: 06/01/2012 TariffController.Java: This class class
 * handles all functionality related to the price of the handset. The
 * administrator can add new tariffs or update and delete existing ones.
 */

@Controller
@SessionAttributes
public class TariffController {

	@Autowired
	private TariffService tariffService;
	@Autowired
	private TariffDBDao tariffDBDao;

	@RequestMapping("/manageTariffs.htm")
	@ModelAttribute
	public ModelAndView getTariffManager(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "type") String type) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("type", type);

		if (type.equalsIgnoreCase("getAdd")) {

			try {
				if (request.getParameter("subtype").equalsIgnoreCase("doAdd")) {
					mv.addObject("result", tariffService.addTariff(
							request.getParameter("level"),
							Double.parseDouble(request.getParameter("price"))));
					mv.addObject("subtype", request.getParameter("subtype"));
				}
			} catch (Exception e) {

			}
		}

		else if (type.equalsIgnoreCase("getpage")) {
			TableModel tableModel = new TableModel("Tariffs", request, response);
			tableModel.setItems(tariffDBDao.getTariffs());
			tableModel.setStateAttr("restore");

			// Web Option

			tableModel.setTable(getTable(type));
			String html = tableModel.render();
			request.setAttribute("output", html);

		}

		mv.addObject("type", type);
		mv.setViewName("manageTariffs");

		return mv;

	}

	@RequestMapping("/tariffDetails.htm")
	@ModelAttribute
	public ModelAndView getTariffDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id") int id,
			@RequestParam(value = "type") String type) {

		ModelAndView mv = new ModelAndView();

		// process users request. can be edit, delete or view individual tariff

		if (type.equalsIgnoreCase("getEdit") || type.equalsIgnoreCase("doEdit")
				|| type.equalsIgnoreCase("delete")) {
			boolean result = false;

			// administrator request to delete tariff
			if (type.equalsIgnoreCase("delete")) {

				result = tariffService.deleteTariff(id);

			}

			// administrator edits tariff details. return status to view
			else if (type.equalsIgnoreCase("doEdit")) {

				result = tariffService.updateTariff(id,
						Double.parseDouble(request.getParameter("price")),
						request.getParameter("level"));

			}

			mv.addObject("result", result);
		}

		else {

			mv.addObject("tariff", tariffDBDao.getIndividualTariff(id));

		}

		mv.addObject("id", id);
		mv.addObject("type", type);
		mv.setViewName("tariffDetails");

		return mv;

	}

	// Set up web view with jmesa table rendering API

	private Table getTable(String type) {

		HtmlTable htmlTable = new HtmlTable();
		HtmlRow htmlRow = new HtmlRow();
		htmlTable.setRow(htmlRow);
		htmlRow.addColumn(buildLinkColumn("tariffID", "Manage",
				ColumnType.OPTION, type));
		htmlRow.addColumn(buildColumn("level", "Level", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("price", "Price", ColumnType.NUMBER));

		return htmlTable;
	}

	private Column buildColumn(String var, String name, ColumnType type) {
		Column column = new HtmlColumn(var).title(name);
		return decorateColumn(column, type);
	}

	private Column buildLinkColumn(String var, String name, ColumnType type,
			String stype) {
		Column column = new HtmlColumn(var).title(name);
		return decorateLinkColumn(column, type, stype);
	}

	private Column decorateColumn(Column column, ColumnType type) {
		CellEditor ce = null;
		switch (type) {
		case NUMBER:
			ce = new NumberCellEditor("###,##0.00");
			break;
		default:

		}

		if (ce != null) {
			column.cellEditor(ce);
		}

		return column;
	}

	private Column decorateLinkColumn(Column column, ColumnType type,
			String stype) {
		CellEditor ce = null;

		ce = setLink(stype);

		if (ce != null) {
			column.cellEditor(ce);
		}

		return column;
	}

	private enum ColumnType {
		NUMBER, NORMAL, OPTION;
	}

	// set hyperlink for administrator to select individual tariff from
	public CellEditor setLink(final String type) {

		return new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new HtmlCellEditor().getValue(item, property,
						rowcount);
				HtmlBuilder html = new HtmlBuilder();

				html.a()
						.href()
						.quote()
						.append("tariffDetails.htm?type=" + type + "&id="
								+ value.toString()).quote().close();
				html.append(value);
				html.aEnd();

				return html.toString();
			}
		};
	}

}
