//分页的表单form的 id 用 pageForm

//跳转到第一页
function pageToFirst()
{
	$('#pg-goto-num').val('1');
	submitForm();
}
//上一页
function pageToPre()
{
	//alert($('#current_page').val());
	var pageIndex = Number($('#pg-goto-num').val()) - 1;
	$('#pg-goto-num').val(pageIndex);
	submitForm();
}

//下一页
function pageToNext()
{
	 
	var pageIndex = Number($('#pg-goto-num').val()) + 1;
	$('#pg-goto-num').val(pageIndex);
	submitForm();
}
//最后一页
function pageToLast(num)
{
	$('#pg-goto-num').val(num);
	submitForm();
}

function pageTo(num)
{	
	$('#pg-goto-num').val(num);
	submitForm();
}


function pageGoTo(num)
{	
	if($('#pg-goto-num').val()<1)
	{
		$('#pg-goto-num').val(1);
	}
	if($('#pg-goto-num').val()>num)
	{
		$('#pg-goto-num').val(num);
	}
	submitForm();
}
function submitForm()
{
	$('#pageForm').submit();
}

