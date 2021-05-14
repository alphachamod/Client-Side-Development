
$(document).ready(function()
{
    if ($("#alertSuccess").text().trim() == "")
    {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
    // Alert
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();

    // Validation
    var status = validate_productForm();
    if (status != true)
    {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If true
    var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

    $.ajax(
        {
            url : "ProductAPI",
            type : type,
            data : $("#productForm").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onProductSaveComplete(response.responseText, status);
            }
        });
});


$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
        {
            url : "ProductAPI",
            type : "DELETE",
            data : "p_id=" + $(this).data("productid"),
            dataType : "text",
            complete : function(response, status)
            {
                onItemDeleteComplete(response.responseText, status);
            }
        });
});

$(document).on("click", ".btnUpdate", function(event)
{
    $("#hidItemIDSave").val($(this).data("acc_id"));
    $("#p_id").val($(this).closest("tr").find('td:eq(0)').text());
    $("#p_name").val($(this).closest("tr").find('td:eq(1)').text());
    $("#innovator_name").val($(this).closest("tr").find('td:eq(2)').text());
    $("#initial_price").val($(this).closest("tr").find('td:eq(3)').text());
    $("#stock_amount").val($(this).closest("tr").find('td:eq(1)').text());
    $("#product_category").val($(this).closest("tr").find('td:eq(2)').text());

});

function onProductSaveComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#table").html(resultSet.data);
        } else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error")
    {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else
    {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hidItemIDSave").val("");
    $("#productForm")[0].reset();
}


//validation on the form
function validate_productForm()
{
    //ID
    var id = $("#p_id").val().trim();
    if (!$.isNumeric(id))
    {
        return "Insert a  value for ID.";
    }
    // Product Name
    if ($("#p_name").val().trim() == "")
    {
        return "Insert Product Name";
    }
    // innovator_name-------------------------------
    if ($("#innovator_name").val().trim() == "")
    {
        return "Insert the Name of the innovator";
    }
    // initial_price-------------------------------
    if ($("#initial_price").val().trim() == "")
    {
        return "Insert the Initial Price";
    }
    // stock_amount
    var sA = $("#stock_amount").val().trim();
    if (!$.isNumeric(sA))
    {
        return "Insert the Stock Amoount.";
    }
    // product_category------------------------
    if ($("#product_category").val().trim() == "")
    {
        return "Insert a Product Category";
    }

    return true;
}