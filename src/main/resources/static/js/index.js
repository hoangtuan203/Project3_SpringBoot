
$(document).ready(function() {
    $('.table-row input[type="checkbox"]').change(function() {
        // Nếu checkbox đang được chọn
        if ($(this).prop('checked')) {
            // Bỏ chọn tất cả các checkbox khác
            $('.table-row input[type="checkbox"]').not(this).prop('checked', false);
        }
    });
    
    $('.table-row').hover(function() {
        $(this).toggleClass('hovered');
    });

    $('.table-row').click(function() {
        var isChecked = $(this).find('input[type="checkbox"]').prop('checked');

        // Nếu checkbox đã được chọn, bỏ chọn khi nhấn vào
        if (isChecked) {
            $(this).find('input[type="checkbox"]').prop('checked', false);
        } 
        // Ngược lại, chọn checkbox khi nhấn vào
        else {
            $(this).find('input[type="checkbox"]').prop('checked', true);
        }
        
        $('.table-row input[type="checkbox"]').click(function(event) {
            event.stopPropagation();
        });
        
    
    });
    
    



    $('#btnDatCho').click(function() {
        // Tạo một mảng để lưu trữ các thuộc tính được chọn
        var selectedItems = [];

        // Duyệt qua các checkbox được chọn
        $('.table-row input[type="checkbox"]:checked').each(function() {
            // Lấy các thuộc tính của dòng được chọn và thêm vào mảng
            var maTB = $(this).closest('.table-row').find('.maTB').text();
            
            var tenTB = $(this).closest('.table-row').find('.tenTB').text();
    
            selectedItems.push({ maTB: maTB, tenTB: tenTB });
            
        });
        
        // Hiển thị form popup và điền thông tin từ mảng vào form
        $('#popup').show();
        $('#popup-tenTB').html('');
        // $('#popup .content').html(''); // Xóa nội dung cũ của form trước khi điền mới
        selectedItems.forEach(function(item) {
            $('#popup-tenTB').text(item.tenTB);
            $('#maTBHiden').val(item.maTB);
            // $('#popup .content').append('<p>Tên thiết bị: ' + item.tenTB + '</p>');
            // $('#popup .content').append('<p>Thời gian: </p>');
            // $('#popup .content').append('<input type="text" id="datepicker" placeholder="Chọn ngày">');
            // $('#popup .content').append('<input type="text" id="timepicker" placeholder="Chọn thời gian">');
        });
        
    });
    $('#datetimepicker').datepicker({
        format: 'yyyy-mm-d'
        // todayBtn: true,
        // todayHighlight: true,
        // minView: 'hour',
        // startDate: new Date(),
    });
    $('#datetimepicker2').datetimepicker({
        format: 'HH:mm:ss'
        // todayBtn: true,
        // todayHighlight: true,
        // minView: 'hour',
        // startDate: new Date(),
    });
    
    $('#popup-close').click(function() {
       $('#popup').hide();
    });
});

    