<%--
  Created by IntelliJ IDEA.
  User: KhoaBug
  Date: 7/16/2023
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editConfirmModal" data-bs-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Luu thay doi</h5>
                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">Ban co chac chan muon luu thay doi</div>
            <div class="modal-footer">
                <%--@declare id="editform"--%>
                <button class="btn btn-secondary" type="button"
                        data-bs-dismiss="modal">Khong
                </button>
                <button class="btn btn-primary"
                        type="submit" form="editForm">Co a
                </button>
            </div>
        </div>
    </div>
</div>
