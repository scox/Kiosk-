package com.kiosk.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.model.TableModel;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.editor.NumberCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.editor.HtmlCellEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kiosk.model.Audio;
import com.kiosk.service.AudioService;
import com.kiosk.service.TariffService;

@Controller
@SessionAttributes
public class AudioController {

	@Autowired
	private AudioService audioService;

	@Autowired
	private TariffService tariffService;

	@RequestMapping("/manageAudios.htm")
	@ModelAttribute
	public void getAudioManager(Object command, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "type") String type) {

		if (type.equalsIgnoreCase("getAdd")) {

			model.put("level", tariffService.getTariffs());
		}
		List<Audio> audio = audioService.getAudio();

		TableModel tableModel = new TableModel("Audios", request, response);
		tableModel.setItems(audio);
		tableModel.setStateAttr("restore");

		// Web Option

		tableModel.setTable(getTable());
		String html = tableModel.render();
		request.setAttribute("output", html);
		model.put("command", new Audio());
		model.put("type", type);

	}

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
	}

	@RequestMapping("/audioDetails.htm")
	@ModelAttribute
	public ModelAndView getViewAudios(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id") int id,
			@RequestParam(value = "type") String type, Object command) {

		ModelAndView mv = new ModelAndView();

		if (type.equalsIgnoreCase("delete")) {

			mv.addObject("result", audioService.deleteAudio(id));

		}

		else {
			mv.addObject("audio", audioService.getIndividualAudio(id));
			System.out.println(audioService.getIndividualAudio(id)
					.getLanguage() + " l");
			mv.addObject("level", tariffService.getTariffs());
			mv.addObject("command", new Audio());
		}

		mv.addObject("id", id);
		mv.addObject("type", type);
		mv.setViewName("audioDetails");

		return mv;

	}

	// Set up web view

	private Table getTable() {

		HtmlTable htmlTable = new HtmlTable();
		HtmlRow htmlRow = new HtmlRow();
		htmlTable.setRow(htmlRow);
		htmlRow.addColumn(buildLinkColumn("audioID", "Audio ID",
				ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("language", "Language", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("level", "Level", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("dateCreated", "Date Created",
				ColumnType.DATE));
		htmlRow.addColumn(buildColumn("roomNo", "Room No", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("title", "Title", ColumnType.NORMAL));
		htmlRow.addColumn(buildColumn("description", "Description",
				ColumnType.NORMAL));

		return htmlTable;
	}

	private Column buildColumn(String var, String name, ColumnType type) {
		Column column = new HtmlColumn(var).title(name);
		return decorateColumn(column, type);
	}

	private Column buildLinkColumn(String var, String name, ColumnType type) {
		Column column = new HtmlColumn(var).title(name);
		return decorateLinkColumn(column, type);
	}

	private Column decorateColumn(Column column, ColumnType type) {
		CellEditor ce = null;
		switch (type) {
		case NUMBER:
			ce = new NumberCellEditor("###,##0.00");
			break;
		case DATE:
			ce = new DateCellEditor("dd/MMM/yyyy");
			break;
		default:

		}

		if (ce != null) {
			column.cellEditor(ce);
		}

		return column;
	}

	private Column decorateLinkColumn(Column column, ColumnType type) {
		CellEditor ce = null;

		ce = setLink();

		if (ce != null) {
			column.cellEditor(ce);
		}

		return column;
	}

	private enum ColumnType {
		NUMBER, NORMAL, OPTION, DATE;
	}

	public CellEditor setLink() {

		return new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new HtmlCellEditor().getValue(item, property,
						rowcount);
				HtmlBuilder html = new HtmlBuilder();

				html.a()
						.href()
						.quote()
						.append("audioDetails.htm?type=manage&id="
								+ value.toString()).quote().close();
				html.append(value);
				html.aEnd();

				return html.toString();
			}
		};
	}

}
