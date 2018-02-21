$(function(){
	montarTabelaNoticias();
	listarNoticias();
	
	$("#btnNovaNoticia").click(function(){
		$("#modalCadastroNoticia").modal("show");
	});
	
	$("#btnSubmeterPDF").click(function(){
		$("#modalSubmeterPdf").modal("show");
	});
	
	$("#btnSubmeter").click(function(){
		console.log("upload...")
		$("#btnSubmeter").prop("disabled", true);
		salvarNoticiaPDF($("#formNoticiaPdf").serialize());
	});
	
	$("#btnSalvarNoticia").click(function(){
		salvarNoticia($("#formNoticia").serialize());
	});
	
	
});

function fecharModalNoticia(){
	$("#modalCadastroNoticia").modal("hide");
	$("#idNoticia").val("");
	$("#titulo").val("");
	$("#noticia").val("");
	$("#arquivoUpload").val("");
	listarNoticias();
}

function salvarNoticia(form) {
	form += '&tipo=PADRAO';
	form += '&edicao.id='+$("#idEdicao").val();
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/noticias/salvar',
		data: form,
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			var idGerado = data.idGerado;
			if(idGerado !== undefined && idGerado!== null){
				uploadImagem(idGerado);
			}
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}


function salvarNoticiaPDF(form) {
	form += '&tipo=PDF';
	form += '&edicao.id='+$("#idEdicao").val();
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/noticias/salvar',
		data: form,
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			var idGerado = data.idGerado;
			console.log(idGerado);
			if(idGerado !== undefined && idGerado !== null){
				uploadNoticia(idGerado);
				fecharModalSubmeterPdf();
			}
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function listarNoticias(){
	var idEdicao = $("#idEdicao").val();
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'get',
		url : UtilsJS.context_path() + '/noticias/edicao/'+idEdicao+'/listar',
		beforeSend : function() {1
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			if (data.id == 1) {
				 $('#tabela_noticias').bootstrapTable('load', data.resultObject);
			} else if (data.id == 2) {
				console.log(data.mensagem);
			} else {
				console.log(data.mensagem);
			}
		},
		error : function() {
			console.log("Erro");
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function deletarNoticia(row){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/noticias/deletar',
		data: {
			id: row.id,
			titulo: row.titulo,
			tipo: row.tipo
		},
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			listarNoticias();
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}


function uploadImagem(id) {
	var arquivo = $('#arquivoUpload')[0].files[0];
	var formData = new FormData();
	formData.append('file', arquivo);
	
	if(arquivo != undefined){
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : UtilsJS.context_path() + "/noticias/uploadimagem/noticia/"+id,
			data : formData,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			beforeSend : function() {
				$('#boxModalNoticia').find('.overlay').show();
			},
			success : function(data) {
				fecharModalNoticia();
			},
			error : function(e) {
			},
			complete : function(data) {
				$('#boxModalNoticia').find('.overlay').hide();
			}
		});
	} else {
		fecharModalNoticia();
	}

}


function uploadNoticia(id) {
	var arquivo = $('#fileUpload')[0].files[0];
	var formData = new FormData();
	formData.append('file', arquivo);
	
	console.log($('#fileUpload').val());
	console.log(arquivo);
	console.log(formData);
	
	if(arquivo != undefined){
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : UtilsJS.context_path() + "/noticias/upload/noticia/"+id,
			data : formData,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			beforeSend : function() {
				$('#boxModalUpload').find('.overlay').show();
			},
			success : function(data) {
				
			},
			error : function(e) {
			},
			complete : function(data) {
				$('#boxModalUpload').find('.overlay').hide();
			}
		});
	}else{
		$("#btnSubmeter").prop("disabled", false);
	}

}

function fecharModalSubmeterPdf(){
	$("#modalSubmeterPdf").modal("hide");
	$("#idNoticiaPdf").val('');
	$("#tituloPdf").val('');
	$("#fileUpload").val('');
	listarNoticias();
}

function montarTabelaNoticias(){
	$('#tabela_noticias').bootstrapTable('destroy');
    $('#tabela_noticias').bootstrapTable({
        cache: false,
        striped: true,
        clickToSelect: true,
        pagination: true,
        pageSize: 7,
        pageList: [7],
        uniqueId: 'id',
        onDblClickRow: function (row, $element) {
        },
        columns: [{
            field: 'id',
            visible: false
        }, {
            field: 'titulo',
            title: 'Noticia',
            align: 'left',
            valign: 'middle'
        }, {
        	title: 'Editar',
        	align: 'center',
        	valign: 'middle',
            events: operateEvents,
            formatter: editFormatter
        }, {
	         title: 'Excluir',
	         align: 'center',
	         valign: 'middle',
	         events: operateEvents,
	         formatter: removeFormatter
		}]
    });
}

function editFormatter(value, row, index) {
	return [
        '<a class="edit" href="javascript:void(0)" title="Clique para editar">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
    ].join('');
}
function removeFormatter(value, row, index) {
	return [
        '<a class="remove" href="javascript:void(0)" title="Clique para excluir">',
        '<i class="fa fa-trash"></i>',
        '</a>  ',
    ].join('');
}
window.operateEvents = {
		'click .edit': function (e, value, row, index) {
			console.log("Editar");
			if(row.tipo==="PADRAO"){
				$("#idNoticia").val(row.id);
		    	$("#titulo").val(row.titulo);
		    	$("#noticia").val(row.texto);
		    	$("#modalCadastroNoticia").modal("show");
			} else {
				$("#idNoticiaPdf").val(row.id);
				$("#tituloPdf").val(row.titulo);
				$("#modalSubmeterPdf").modal("show");
			}
	    	
	    },
		'click .remove': function (e, value, row, index) {
			console.log("Remover");
			deletarNoticia(row);
		}
};