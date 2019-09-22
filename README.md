# GitRepos
gitrepos application

Burada kullanılan bazı 3rd party kütüphaneler vardır. Bunlar; 
  - Retrofit 
  Bu kütüphane connection sağlamak için kullanılmıştır.
  - Butterknife
  Bu kütüphane layout.xml file'larındaki layout objelerine bind olmak ve ekranda o objeleri yönetmek için kullanılmıştır.
  - Glide
  Bu kütüphane ilgili linkten image download edip ilgili imageview de göstermek için kullanılmıştır.
  
  Ayrıca network katmanı ayrı bir modul olarak yazılmıştır. Uygulama katmanı ile network katmanı ayrılmıştır.
  Kullanılan tüm class'lar düzgün şekilde package'lanmaya çalışılmıştır.
  Bazı class'lar için base class'lar oluşturulmuştur, bu sayede OOP sağlanılmaya çalışılmıştır.
  
  Uygulama genelinde tüm string datası ayrı bir yerden okunmuştur, olası dil değişikliğinde kolayca uyumlanabilsin diye.
  
  Yine aynı şekilde uygulama genelindeki tüm layout operasyon işlemleri dimen.xml file'ından okunmuştur, çözünürlük
  bazlı sorunları minimuma indirmek için.
  
  Ekranlarda constraintlayout kullanılmaya özen gösterilmiştir, layout bazlı performans sorunlarını ortadan kaldırmak için.
  
  
  
