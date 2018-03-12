package org.jiumao.example.utils.textFilter.test;

import java.util.List;

import org.jiumao.example.utils.strings.RegexUtil;
import org.jiumao.example.utils.textFilter.TextFormat;


class MacFormatImpl implements TextFormat {

	@Override
	public List<String> doWithFormat(String text) {
		return RegexUtil.patternText(text, RegexUtil.IS_MAC_ADDR);
	}

}
