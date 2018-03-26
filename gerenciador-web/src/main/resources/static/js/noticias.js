$(function(){
	montarTabelaNoticias();
	listarNoticias();
	
	$("#btnNovaNoticia").click(function(){
		$("#modalCadastroNoticia").modal("show");
		$("#boxModalImagensNoticia").hide();
		montarTabelaImagensNoticia();
	});
	
	$("#btnSubmeterPDF").click(function(){
		$("#modalSubmeterPdf").modal("show");
		$("#divUploadPdf").show();
	});
	
	$("#btnSubmeter").click(function(){
		console.log("upload...")
		$("#btnSubmeter").prop("disabled", true);
		salvarNoticiaPDF($("#formNoticiaPdf").serialize());
	});
	
	$("#btnSalvarNoticia").click(function(){
		console.log("Salvar noticia")
		salvarNoticia($("#formNoticia").serialize());
	});
	
	$("#btnSalvarEdicao").click(function(){
		console.log("Salvar Edicao");
		salvarEdicao($("#formEdicao").serialize());
	});
	
	$("#btnUploadImagemNoticia").click(function(){
		var idNoticia = $("#idNoticia").val();
		if(idNoticia !== null && idNoticia !== undefined && idNoticia !== ''){
			uploadImagem(idNoticia);
		}
	});
	
	$("#modalCadastroNoticia").on('hidden.bs.modal', function () {
		$("#idNoticia").val("");
		$("#titulo").val("");
		$("#noticia").val("");
		$("#arquivoUpload").val("");
		listarNoticias();
	});
	
	$("#modalSubmeterPdf").on('hidden.bs.modal', function () {
		$("#idNoticiaPdf").val('');
		$("#tituloPdf").val('');
		$("#fileUpload").val('');
		listarNoticias();
	});
	
});

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
			if(data.idGerado !== null && data.idGerado !== undefined && data.idGerado !== ''){
				$("#idNoticia").val(data.idGerado);
				$("#boxModalImagensNoticia").show();
			}
		},
		error : function() {
		},
		complete : function() {
			$('#boxEdicoes').find('.overlay').hide();
		}
	});
}

function salvarEdicao(form){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/edicao/salvar',
		data: form,
		beforeSend : function() {
//			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			if (data.id == 1) {
				swal("Sucesso", "Edição salva com sucesso !", "success")
//				var edicao = data.resultObject;
//				window.location.href = UtilsJS.context_path() + '/noticias/edicao/'+edicao.id;
			} else if (data.id == 2) {
				criarAlert('informacaoEdicao', 'warning', data.mensagem);
			} else {
				criarAlert('informacaoEdicao', 'error', data.mensagem);
			}
		},
		error : function() {
			criarAlert('informacaoEdicao','error','Ocorreu um erro durante ao salvar a edição, por favor, tente novamente mais tarde.');
		},
		complete : function() {
//			$('#boxEdicoes').find('.overlay').hide();
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


function listarImagensNoticia(idNoticia){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'get',
		url : UtilsJS.context_path() + '/noticias/listar-imagens/noticia/'+idNoticia,
		beforeSend : function() {1
			$('#boxModalImagensNoticia').find('.overlay').show();
		},
		success : function(data) {
			if (data.id == 1) {
				 $('#tabela_imagens_noticia').bootstrapTable('load', data.resultObject);
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
			$('#boxModalImagensNoticia').find('.overlay').hide();
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

function deletarImagemNoticia(row){
	$.ajax({
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : 'post',
		url : UtilsJS.context_path() + '/noticias/deletar-imagem/',
		data: {
			id: row.id,
			nomeOriginal: row.nomeOriginal,
			nome: row.nome,
			extensao: row.extensao,
			caminho: row.caminho,
			tipo: row.tipo
		},
		beforeSend : function() {
			$('#boxEdicoes').find('.overlay').show();
		},
		success : function(data) {
			var idNoticia = $("#idNoticia").val();
			listarImagensNoticia(idNoticia);
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
				$("#arquivoUpload").val("");
				listarImagensNoticia(id);
			},
			error : function(e) {
			},
			complete : function(data) {
				$('#boxModalNoticia').find('.overlay').hide();
			}
		});
	} else {
		alert("Falha ao salvar noticia");
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
}

function montarTabelaImagensNoticia(){
	$('#tabela_imagens_noticia').bootstrapTable('destroy');
    $('#tabela_imagens_noticia').bootstrapTable({
        cache: false,
        striped: true,
        clickToSelect: true,
        pagination: true,
        pageSize: 7,
        pageList: [7],
        uniqueId: 'id',
        onDblClickRow: function (row, $element) {
        },
        columns: [ {
            field: 'id',
            title: 'Id',
            align: 'left',
            valign: 'middle'
        }, {
        	field: 'nomeOriginal',
        	title: 'Nome',
        	align: 'center',
        	valign: 'middle',
        }, {
	         title: 'Excluir',
	         align: 'center',
	         valign: 'middle',
	         events: operateEvents,
	         formatter: removeImageFormatter
		}]
    });
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
function removeImageFormatter(value, row, index){
	return [
        '<a class="remove_image" href="javascript:void(0)" title="Clique para excluir">',
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
		    	$("#boxModalImagensNoticia").show();
		    	montarTabelaImagensNoticia();
		    	listarImagensNoticia(row.id);
			} else {
				$("#idNoticiaPdf").val(row.id);
				$("#tituloPdf").val(row.titulo);
				$("#modalSubmeterPdf").modal("show");
				$("#divUploadPdf").hide();
			}
	    	
	    },
		'click .remove': function (e, value, row, index) {
			console.log("Remover");
			swal({
				  title: "Atenção",
				  text: "Realmente deseja deletar a notícia ?",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					  deletarNoticia(row);
				  } 
				});
		},
		'click .remove_image': function (e, value, row, index) {
			console.log("Remover");
			swal({
				  title: "Atenção",
				  text: "Realmente deseja deletar a imagem ?",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					  deletarImagemNoticia(row)
				  } 
				});
		}
};