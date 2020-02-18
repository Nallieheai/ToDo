package assets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

// Formats the selected date in the JDatePicker to a string that can be output
// in a label or text field
public class DateLabelFormatter extends AbstractFormatter {

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar calendar = (Calendar) value;
			return dateFormatter.format(calendar.getTime());
		} else return "";
	}
}
