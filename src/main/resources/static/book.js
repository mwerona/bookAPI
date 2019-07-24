var searchResult;
var tempURL = "";
var xhr = new XMLHttpRequest();

function flip() {
	document.querySelector(".mainContainer").classList.toggle("flip");
}

function init() {
	getTop10();
}

function getTop10() {
	xhr.open('GET', 'keywordTop10', true);
	xhr.send();
	xhr.onreadystatechange = function() {
		searchResult = this.responseText;

		if (searchResult != null) {
			var tableData = JSON.parse(searchResult);
			var table = document.getElementById('keywordTable');
			var row = {};
			var cell = {};
			table.innerHTML = "";
			tableData.forEach(function(rowData) {
				row = table.insertRow(-1);
				row.insertCell().textContent = rowData.keyword;
				row.insertCell().textContent = rowData.count + "회";
			});
			document.getElementById("keywordDiv").appendChild(table);
		}
	}
}

function getHistory() {
	xhr.open('GET', 'getHistory', true);
	xhr.send();
	xhr.onreadystatechange = function() {
		searchResult = this.responseText;

		if (searchResult != null) {
			var tableData = JSON.parse(searchResult);
			var table = document.getElementById('histroyTable');
			var row = {};
			var cell = {};
			table.innerHTML = "";
			tableData.forEach(function(rowData) {
				row = table.insertRow(-1);
				row.insertCell().textContent = rowData.keyword;
				row.insertCell().textContent = rowData.searchtime;
			});
			document.getElementById("historyDiv").appendChild(table);
		}
	}
}

function search() {
	// document.doSearch.submit();
	var data = "", tv = document.getElementById('target').value, kv = document
			.getElementById('keyword').value;
	if (kv != null && kv != "") {
		data += '?keyword=' + kv;
		if (tv != "")
			data += '&target=' + tv;

		xhr.open('GET', 'search' + encodeURI(data), true);
		xhr.send();

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				searchResult = this.responseText;
				if (searchResult != null) {
					var temp = JSON.parse(searchResult);
					if (temp.meta.total_count <= 0) {
						alert("검색 결과가 없습니다.")
					} else {
						document.getElementById('popupSearch').style.display = "none";
						document.getElementById('popupResult').style.display = "block";
						document.getElementById('page').placeholder = "1 / "
								+ temp.meta.pageable_count;
						tempURL = data;

						var table = document.getElementById('searchTable');
						var row = {};
						var cell = {};
						table.innerHTML = "";
						temp.documents.forEach(function(rowData) {
							row = table.insertRow(-1);
							row.insertCell().textContent = rowData.title;
							row.insertCell().textContent = rowData.authors;
							row.insertCell().textContent = rowData.contents;
							row.insertCell().textContent = rowData.datetime;
							row.insertCell().textContent = rowData.isbn;
							row.insertCell().textContent = rowData.price;
							row.insertCell().textContent = rowData.sale_price;
							row.insertCell().textContent = rowData.publisher;
							row.insertCell().textContent = rowData.status;
							row.insertCell().textContent = rowData.thumbnail;
							row.insertCell().textContent = rowData.translators;
							row.insertCell().textContent = rowData.url;
						});
						document.getElementById("searchDiv").appendChild(table);

					}
				}

			}
		};
	}
}

function goPage() {
	var pageNum = document.getElementById('page').value;
	xhr.open('GET', 'search' + encodeURI(tempURL + "&page=" + pageNum), true);
	xhr.send();

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			searchResult = this.responseText;
			if (searchResult != null) {
				document.getElementById('page').placeholder = pageNum + " / "
					+ temp.meta.pageable_count;
				
				var table = document.getElementById('searchTable');
				var row = {};
				var cell = {};
				table.innerHTML = "";
				temp.documents.forEach(function(rowData) {
					row = table.insertRow(-1);
					row.insertCell().textContent = rowData.title;
					row.insertCell().textContent = rowData.authors;
					row.insertCell().textContent = rowData.contents;
					row.insertCell().textContent = rowData.datetime;
					row.insertCell().textContent = rowData.isbn;
					row.insertCell().textContent = rowData.price;
					row.insertCell().textContent = rowData.sale_price;
					row.insertCell().textContent = rowData.publisher;
					row.insertCell().textContent = rowData.status;
					row.insertCell().textContent = rowData.thumbnail;
					row.insertCell().textContent = rowData.translators;
					row.insertCell().textContent = rowData.url;
				});
				document.getElementById("searchDiv").appendChild(table);
			}

		}
	};
}

function closeResult() {
	document.getElementById('popupSearch').style.display = "block";
	document.getElementById('popupResult').style.display = "none";
}