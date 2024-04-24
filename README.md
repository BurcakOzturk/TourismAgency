# <p align="center">Turizm Acente Uygulaması</p>

## <p align="center">Turizm Acentesi yönetici ve çalışan kullunıcılarına sahip bir otel işletme projesidir.</p>

## <p align="center">Projenin kullanımı ile ilgili detayları tanıtım linki üzerinden inceleyebilirsiniz.</p>
## <p align="center">https://www.youtube.com/watch?v=-40SChb_MEU</p>

### Yönetici olarak giriş yapan kullanıcılar:
  - Yönetici veya çalışan olarak yetklendirilen personel listesini rolüne göre görüntüleyebilir.
  - Ekleme, çıkarma, silme, güncelleme ve filitreleme  işlemleri gerçekleştirebilir.
  
### Çalışan olarak giriş yapan kullanıcılar ise otel rezervasyon işlemlerini kontrol eder. Bu işlemler:
  - Yeni otel ekleme, mevcut otellerı görüntüleme ve düzenleme yapabilir veya silebilir.
  - Yeni oda ekleme, mevcut odaları görüntüleme ve düzenleme yapabilir veya silebilir.
  - Mevcut otele pansiyon ve sezon ekleme işlemleri yapabilir veya silebilir.
  - Sezon eklemesi sırasında fiyat katsayısını değiştirip, sezona göre fiyatlandırma işlemi yapabilir.
  
### Çalışan olarak giriş yapan kullanıcılar ayrıca;
  - Tarih aralığı, otel adı veya otel şehri değişkenlerine göre müsait odaları ve otelleri görüntüleyebilir.
  - Arama sonucunda çıkan odalara kişi rezervasyonu yapıp, bu rezervasyonu düzenleyebilir veya silebilir.
  

### <p align="center">Proje Java 21 ile yazılmıştır, veritabanı denetimi için PostgreSQL programı kullanılmıştır. Swing ile arayüz tasarlanmıştır.</p>
##
# <p align="center">Ekran Görüntüleri</p>
### 1. Kullanıcı Giriş Ekranı
<p align="center">
    <img width="400" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/49111da6-7486-4c5c-9d92-67159210c315">
</p>

### 2. Yönetici Ekranı
<p align="center">
    <img width="800" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/a94a0aa0-85f9-428e-95dd-a10559a73dad">
</p>

### 3. Çalışan Ekranı
<p align="center">
    <img width="800" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/54bed257-a3bb-4b1a-94bb-01b6d37adbe5">
</p>

### 4. Rezervasyon Ekranı
<p align="center">
    <img width="800" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/ea46e1d4-7cf0-466d-9fa2-d1df3afadd20">
</p>

##
# <p align="center">Veritabanı Tabloları</p>
### Projede kullanılan veritabanı, kodun anadizini içerisine eklenmiştir. İsternirse kendi sisteminize ekleyip deneyebilirsiniz.

### 1. Otel Tablosu

Otel tablosunda yer alan bilgiler aşağıdaki gibidir. Bu bilgiler çalışan tarafında arayüz üzerinden doldurulup sonradan düzenlenebilir şekildedir.
<p align="center">
    <img width="1000" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/559f70e7-da99-44b6-a254-53b742ca2551">
</p>

### 2. Oda Tablosu
Oda tablosunda yer alan bilgiler aşağıdaki gibidir. Bu bilgiler çalışan tarafında arayüz üzerinden doldurulup sonradan düzenlenebilir şekildedir.
<p align="center">
    <img width="1000" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/991a5b5b-a471-4abe-867e-ecf284eaf7c7">
</p>

### 3. Rezervasyon Tablosu
Rezervasyon tablosunda yer alan bilgiler aşağıdaki gibidir. Bu bilgiler çalışan tarafında arayüz üzerinden doldurulup sonradan düzenlenebilir şekildedir.
<p align="center">
    <img width="1000" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/1dd22834-db9c-463e-ace0-b854179aea70">
</p>

### 4. Pansiyon Tablosu
Pansiyon tipi tablosundaki veriler, çalışan tarafından seçilen otele bağlanarak oluşturulmaktadır.
<p align="center">
    <img width="600" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/9e067f58-aeb4-41a4-8e30-ac3b6dd5e8e6">
</p>

### 5. Sezon Tablosu
Sezon tablosundaki verilen, çalışan tarafından seçilen otele bağlanarak oluşturulmaktadır. Price parameter sütununda o döneme ait fiyat katsayısı tutulup, döneme göre fiyat değişimleri yapılması sağlanmaktadır.
<p align="center">
    <img width="600" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/fc819c53-018d-41fb-9dc5-586ca344220c">
</p>

### 6. Kullanıcı Tablosu
Sisteme erişim yapan çalışan ve yöneticilerin bilgilerinin bulunduğu tablodur. Bu tablo program üzerinden, yönetici panelinden değiştirilebilirdir.
<p align="center">
    <img width="600" src="https://github.com/BurcakOzturk/TourismAgency/assets/159647466/efecd831-2235-4a4a-bc48-add26ca7eb32">
</p>
