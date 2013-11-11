(function($$) {
	function InputTree(opts) {
		var _this = this;
		this.m_strNodeValue;
		this.m_strNodeText;
		this.m_jsonData;
		this.m_strSelectValue;
		this.m_strContainerID;
		for (i in opts) {
			if (i.charAt(0) != '_') {
				eval('this.' + i + ' = opts[\'' + i + '\'];');
			}
		}
		this.m_strSpace = "    ";
		this.m_strLine0 = "";
		this.m_strLine1 = "┃";
		this.m_strLine2 = "┣";
		this.m_strLine3 = "┗";
		this.m_iLevel = 1;
		this.p_jqContainer = $("#" + this.m_strContainerID);
		this.p_jqContainer.empty();
		this.m_aryEveryLevelCode = [];
		recursive(_this);
	}
	function recursive(obj) {
		obj.m_aryEveryLevelCode[1] = obj.m_strSpace;
		var bFirst = false;
		for ( var x = 0; x < obj.m_jsonData.length; x++) {
			if (x == 0) {
				bFirst = true;
			} else {
				bFirst = false;
			}
			if (x == obj.m_jsonData.length - 1) {
				obj.m_aryEveryLevelCode[obj.m_iLevel] = obj.m_strSpace;
				createNode(obj.m_jsonData[x].nodes, eval("obj.m_jsonData[x]."
						+ obj.m_strNodeText), eval("obj.m_jsonData[x]."
						+ obj.m_strNodeValue), true, obj.m_iLevel, bFirst);
			} else {
				obj.m_aryEveryLevelCode[obj.m_iLevel] = obj.m_strLine1;
				createNode(obj.m_jsonData[x].nodes, eval("obj.m_jsonData[x]."
						+ obj.m_strNodeText), eval("obj.m_jsonData[x]."
						+ obj.m_strNodeValue), false, obj.m_iLevel, bFirst);
			}
		}
	}
	function createNode(pjsonNodes, pstrText, pstrValue, pbLast, piLevel,
			pbFirst) {
		var _this = this;
		var strCode = createCode(piLevel, pbLast, pbFirst);
		var domOption=createOption(strCode + pstrText, pstrValue);
		if(pbFirst==true){			
			$(domOption).attr("selected", "selected")
		}
		if (pjsonNodes != undefined && pjsonNodes.length > 0) {
			piLevel++;
			p_jqContainer.append(domOption);
			for ( var x = 0; x < pjsonNodes.length; x++) {
				if (x == pjsonNodes.length - 1) {
					_this.m_aryEveryLevelCode[piLevel] = _this.m_strSpace;
					createNode(pjsonNodes[x].nodes, eval("pjsonNodes[x]."
							+ _this.m_strNodeText), eval("pjsonNodes[x]."
							+ _this.m_strNodeValue), true, piLevel);
				} else {
					_this.m_aryEveryLevelCode[piLevel] = _this.m_strLine1;
					createNode(pjsonNodes[x].nodes, eval("pjsonNodes[x]."
							+ _this.m_strNodeText), eval("pjsonNodes[x]."
							+ _this.m_strNodeValue), false, piLevel);
				}
			}
		} else {
			piLevel++;
			p_jqContainer.append(domOption);
		}
	}
	function createCode(deep, last, pbFirst) {
		var lastcode = "";
		if (true == pbFirst && this.m_jsonData.length > 1) {
			lastcode = m_strLine0;
		} else if (true == last) {
			if(true != pbFirst)
				lastcode = m_strLine3;
		} else
			lastcode = m_strLine2;
		var code = "";
		for ( var y = 1; y < deep; y++) {
			code += m_aryEveryLevelCode[y];
		}
		code += lastcode;
		return code;
	}
	function createOption(pstrText, pstrValue) {
		var objOption = document.createElement("option");
		$(objOption).val(pstrValue);
		$(objOption).text(pstrText);
		if (pstrValue == this.m_strSelectValue) {
			$(objOption).attr("selected", "selected");
		}
		return objOption;
	}
	InputTree.prototype = {

	}
	$$.InputTree = {
		New : function(opts) {
			return InputTree(opts);
		}
	}
	return InputTree;
})($$)