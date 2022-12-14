<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="tr">
<head>
<title>BorBi|Uygun Bileti Al!</title>
<jsp:include file="/header.jsp" />
</head>

<jsp:include file="/navbar.jsp" />

<div id="home">
    <div class="hero-text w-100 text-white px-2 px-sm-0">
        <h1 class="display-4">Hoş geldiniz</h1>
        <h3 class="lead mb-4">Nereye Gitmek İstersiniz ?</h3>
        <a href="#booking" class="btn px-5 shadow-none">Filtrele &amp; Seç</a>
    </div>
</div>
<main>
    <div class="search">
        <div class="container fill_height">
            <div class="row fill_height">
                <div class="col fill_height">
                    <div class="search_tabs_container">
                        <div class="search_tabs d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-between justify-content-start">
                            <div class="search_tab active d-flex flex-row align-items-center justify-content-lg-center justify-content-start" id="booking">UÇUŞ SORGULA</div>
                            <div class="search_tab d-flex flex-row align-items-center justify-content-lg-center justify-content-start">REZERVASYON SORGULA</div>
                        </div>
                    </div>
                    <div class="search_panel active">
                        <form action="ucussorgulama" id="search_form_1" method="post" name="ucus" class="search_panel_content d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-between justify-content-start" onsubmit="return Kontrol()">                  
                            <div class="search_item">
                                <div>Gidiş Yeri</div>
                                <select class="search_input" id="gidis" name="gidis" required>
                                    <c:forEach var="havaalani" items="${havaalaniliste}">
                                        <option value="<c:out value="${havaalani.havaalani_id}" />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="search_item">
                                <div>Varış Yeri</div>
                                <select class="search_input" id="varis" name="varis" required>
                                    <c:forEach var="havaalani" items="${havaalaniliste}">
                                        <option value="<c:out value="${havaalani.havaalani_id}" />"><c:out value="${havaalani.havaalani_ad}" /> | <c:out value="${havaalani.havaalani_kod}" /> | <c:out value="${havaalani.havaalani_sehir_ad}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="search_item">
                                <div>Gidiş Tarihi</div>
                                <input type="date" class="search_input" name="gidis_tarih" id="gidis_tarih" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />" required>
                            </div>
                            <div class="search_item">
                                <div>Yetişkin</div>
                                <select name="yetiskin" id="yetiskin" class="dropdown_item_select search_input" required>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                            </div>
                            <div class="search_item">
                                <div>Çocuk</div>
                                <select name="cocuk" id="cocuk" class="dropdown_item_select search_input" required>
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                            </div>
                            <button type="submit" class="search_button">Uçuş Ara</button>                               
                        </form>                            
                    </div>
                    <div class="search_panel">
                        <form action="rezervasyonsorgulama" id="search_form_3" class="search_panel_content d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-between justify-content-start">
                            <div class="search_item">
                                <div>PNR Numaranız</div>
                                <input type="text" name="pnr_no" id="pnr_no" class="search_input"  required="required">
                            </div>
                            <div class="search_item">
                                <div>Soyadınız</div>
                                <input type="text" name="yolcu_soyad" id="yolcu_soyad" class="search_input" required="required">
                            </div>
                            <button type="submit" class="button search_button">Sorgula</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script>
    function Kontrol() {
        var formKontrol = document.forms["ucus"];
        var gidis = formKontrol["gidis"];
        var varis = formKontrol["varis"];
        gidis = gidis.value;
        varis = varis.value;
        if (gidis === varis) {
            swal({
                title: "Hata",
                text: "Gidiş Yeri ve Varış Yeri aynı olamaz!",
                icon: "warning",
                button: "Tamam!",
            });
            return false;
        } else {
            return true;
        }

    }

</script>
<section class="pt-5">
    <div class="intro">
        <h1 class="text-center intro_title">Bilet rezervasyonunuzu BorBi ile yapın!</h1>
        <p class="text-center intro_subtitle">Koşulsuz bilet iptali, online değişiklik, daha fazlası</p>
    </div>
    <div class="">
        <div class="container">
            <div class="card-deck pb-5">
                <div class="card">
                    <img class="intro_card-img-top mx-auto" src="assets/images/safe-ticket.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title text-center">Koşulsuz Bilet İptali</h5>
                        </div>
                </div>
                <div class="card">
                    <img class="intro_card-img-top mx-auto" src="assets/images/magnifier.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title text-center">Online İptal ve Değişiklik</h5>
                        </div>
                </div>
                <div class="card">
                    <img class="intro_card-img-top mx-auto" src="assets/images/kart-saklama-mobil-telefon.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title text-center">Kayıtlı Üye</h5>
                            <p class="card-text text-center">Bilgilerini BorBi'ye kaydet, biletlerini hızlıca al!</p>
                        </div>
                </div>
            </div>
        </div>
    </div>
</section>
<hr class="test_border">
<section>
    <div class="cta mt-5 mb-5">
        <form action="uyeol" method="post">
            <div class="intro text-center">
                <h1 style="font-size: 26px">Üye Ol ve Fırsatları Yakala</h1>
                <a href="uyeol" style="padding-bottom: 15px; padding-top: 15px;" class="search_button">Hemen Üye Ol</a>
        </form>
    </div>
</div>
</section>
<section>
    <div class="desc_bg">
        <div class="container">
            <div class="card-deck pt-5 pb-5">
                <div class="card">
                    <img class="intro_card-img-top mx-auto" src="assets/images/1.svg" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title text-center">İstediğiniz yere BorBi ile gidin</h5>
                            <p class="card-text text-center">En mükemmel seyahati planlamak için seçenekler arasından kendinize uygun olanı bulun.</p>
                        </div>
                </div>
                <div class="card">
                    <img class="intro_card-img-top mx-auto" src="assets/images/3.svg" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title text-center">İstediğiniz gibi seyahat edin..</h5>
                            <p class="card-text text-center">Rezervasyon yapmak için en uygun zamanı görün.</p>
                        </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include file="/footer.jsp" />
