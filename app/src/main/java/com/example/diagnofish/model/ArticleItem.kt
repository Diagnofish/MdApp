package com.example.diagnofish.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.diagnofish.R

data class ArticleItem(
    @DrawableRes val image: Int,
    val title: String,
    val content: String,
)

val dummyArticleItems = arrayOf(
    ArticleItem(R.drawable.aero, "Bacterial diseases - Aeromoniasis", "Penyakit bakterial, terutama Aeromoniasis yang disebabkan oleh bakteri Aeromonas hydrophila, merupakan ancaman serius bagi pembudidaya ikan air tawar. Artikel ini bertujuan memberikan pemahaman mendalam tentang penyakit ini, serta memberikan solusi pencegahan dan pengobatan yang efektif.\n" +
            "1. Pengenalan Aeromoniasis\n" +
            "Aeromoniasis adalah penyakit bakterial yang dapat menyebabkan kematian ikan hingga 80% dalam waktu singkat. Bakteri Aeromonas hydrophila dapat menyerang berbagai jenis ikan, terutama ikan mas, lele, dan patin. Meskipun dapat ditemukan pada ikan air payau dan laut, penyakit ini paling sering terjadi di lingkungan air tawar.\n" +
            "2. Faktor Pemicu Aeromoniasis\n" +
            "Penyakit ini sering terkait dengan kondisi lingkungan yang kurang ideal, seperti kepadatan tinggi, malnutrisi, penanganan yang kurang baik, infeksi parasit, tingginya bahan organik, oksigen rendah, kualitas air yang buruk, dan fluktuasi suhu air yang ekstrim.\n" +
            "3. Gejala dan Dampak\n" +
            "Gejala aeromoniasis meliputi bercak merah di perut, dada, sirip, dan ekor, serta kehilangan lendir, yang membuat ikan rentan terhadap luka. Serangan bersifat akut dan dapat menyebabkan kematian 100% jika kondisi lingkungan memburuk.\n" +
            "4. Pencegahan Aeromoniasis\n" +
            "    • Meningkatkan suhu air, mengganti air secara berkala, dan menjaga kualitas lingkungan.\n" +
            "    • Vaksinasi anti A. hydrophila dan pemberian probiotik sebagai langkah pencegahan efektif.\n" +
            "    • Budidaya ikan yang sehat dengan kondisi lingkungan optimal, pakan berkualitas, dan penanganan yang baik.\n" +
            "5. Pengobatan Aeromoniasis\n" +
            "    • Pengawasan dokter hewan diperlukan untuk memberikan pengobatan yang tepat.\n" +
            "    • Penggunaan garam dapur, antibiotik (Enrofloksasin, Oksitetrasiklin), atau terapi herbal (bawang putih, daun ketapang, daun kelor, daun kirinyuh) sesuai dengan tingkat keparahan penyakit.\n" +
            "6. Langkah-langkah Pencegahan Tambahan\n" +
            "    • Praktik karantina bagi ikan baru untuk mencegah penyebaran penyakit.\n" +
            "    • Pemantauan rutin kesehatan lingkungan budidaya untuk mengurangi risiko serangan bakteri.\n" +
            "7. Kesimpulan\n" +
            "Dengan menjaga kebersihan dan kondisi lingkungan yang baik, pembudidaya ikan dapat meningkatkan produktivitas dan mencegah kerugian yang disebabkan oleh penyakit Aeromoniasis. Kombinasi tindakan pencegahan, vaksinasi, dan pengobatan yang tepat akan meningkatkan keberlanjutan budidaya ikan secara keseluruhan."),
    ArticleItem(R.drawable.gill, "Bacterial gill disease", "Penyakit insang bakterial, atau Bacterial Gill Disease (BGD), umumnya disebabkan oleh bakteri filamen dalam genus Flavobacterium, terutama F. branchiophilum. Penyakit ini menyebabkan pembengkakan lembaran insang, mengurangi pertukaran gas untuk pernapasan ikan. Artikel ini akan membahas gejala, pencegahan, dan pengobatan penyakit ini.\n" +
            "\n" +
            "Gejala\n" +
            "Tanda-tanda klinis Bacterial Gill Disease (BGD) melibatkan gejala kelesuan, hilangnya nafsu makan, desakan di permukaan air, dan respon yang lambat terhadap rangsangan eksternal. Ikan juga dapat berenang tinggi di kolom air dan menyusun diri dengan pola \"seperti tentara.\" Tanda-tanda ini menunjukkan stres tinggi dan, jika tidak diobati, dapat menyebabkan kematian dalam beberapa jam atau hari.\n" +
            "Pada tingkat yang lebih dalam, penyakit ini mempengaruhi filamen insang, menyebabkan pembengkakan hingga filamen tersebut tidak dapat meletakkan diri dengan rata. Ini dapat mengakibatkan pembukaan ujung dorsal operkulum dengan derajat bervariasi. Selain itu, ikan dapat menunjukkan warna tubuh yang gelap, mata yang tersumbat, dan insang nekrotik.\n" +
            "\n" +
            "Pencegahan\n" +
            "Pencegahan BGD pada salmonid di pembenihan sulit dilakukan karena ikan terus-menerus mengalami stres. Sanitasi dan praktik budidaya yang tepat dapat membantu, tetapi tidak menghilangkan sepenuhnya wabah. Pembudidaya sering menggunakan kemoterapi profilaksis dengan bahan kimia sebagai langkah pencegahan, meskipun pencegahan lebih sulit pada ikan kolam daripada pada ikan pembenihan.\n" +
            "\n" +
            "Pengobatan\n" +
            "Pengelolaan Lingkungan:\n" +
            "        ◦ Pemeliharaan kualitas air yang baik.\n" +
            "        ◦ Meningkatkan sirkulasi air.\n" +
            "    • Penggunaan Antibiotik:\n" +
            "        ◦ Antibiotik dapat direkomendasikan oleh ahli hewan atau dokter hewan yang berpengalaman dalam beberapa kasus.\n" +
            "    • Bahan Kimia:\n" +
            "        ◦ Beberapa bahan kimia telah digunakan untuk mengobati BGD. Namun, penggunaannya harus dilakukan dengan hati-hati dan sesuai dengan panduan yang ditetapkan.\n" +
            "Kesimpulan\n" +
            "Penyakit Insang Bakterial (Bacterial Gill Disease) merupakan masalah serius dalam budidaya ikan, terutama pada salmonid. Penting untuk memahami gejala, menerapkan tindakan pencegahan yang efektif, dan menggunakan pengobatan yang tepat agar dapat mengurangi dampak negatif penyakit ini terhadap populasi ikan. Konsultasi dengan ahli hewan atau dokter hewan berpengalaman sangat dianjurkan untuk pengelolaan yang optimal."),
    ArticleItem(R.drawable.red_dis, "Bacterial Red diseases", "Penyakit bercak merah, atau Red Spot Disease (RSD), merupakan kondisi yang disebabkan oleh Aphanomyces invadans, sejenis jamur yang tergolong dalam klasifikasi ilmiah yang sama dengan diatom dan alga coklat. Meskipun umumnya terkait dengan infeksi pada ikan air tawar, bercak merah pada ikan akuarium dan kolam dapat mengindikasikan berbagai masalah, mulai dari masalah kulit hingga infeksi sistemik yang serius. Artikel ini akan membahas gejala, penyebab, dan pengobatan penyakit bercak merah pada ikan.\n" +
            "Gejala\n" +
            "Penyakit bercak merah dapat muncul dalam bentuk bercak kecil seukuran jarum pentul, bercak lebih besar, atau bahkan garis-garis berdarah pada tubuh atau sirip ikan. Selain perubahan fisik, ikan yang terinfeksi juga dapat menunjukkan perubahan perilaku, seperti penurunan nafsu makan, aktivitas rendah, kecenderungan untuk bersembunyi, kesulitan bernafas, dan perilaku renang yang tidak teratur. Infeksi bakteri sekunder dapat memperburuk kondisi, menyebabkan pembusukan sirip, hilangnya warna, dan pembengkakan.\n" +
            "Penyebab\n" +
            "Penyebab penyakit bercak merah dapat bervariasi tergantung pada apakah ikan memiliki lesi tunggal atau bercak merah menyebar di seluruh tubuhnya. Lesi lokal biasanya disebabkan oleh faktor internal tangki, seperti cedera akibat rekan tangki yang agresif, trauma dari benturan dengan permukaan tangki, stres akibat kepadatan populasi yang tinggi, atau parameter kualitas air yang tidak sesuai. Jika bercak merah menyebar secara luas, kemungkinan besar ikan mengalami penyakit sistemik, seperti infeksi bakteri, infeksi parasit, infeksi jamur, atau reaksi toksisitas terhadap zat kimia tertentu.\n" +
            "Pengobatan\n" +
            "Langkah pertama dalam pengobatan adalah mengisolasi ikan yang terkena, membantu mencegah penyebaran penyakit ke ikan lain dan memudahkan proses pengobatan. Jika penyebabnya terkait dengan kondisi tangki, perbaikan parameter kualitas air dan penanganan masalah lainnya diperlukan. Dalam kasus penyakit sistemik, konsultasi dengan dokter hewan akuatik dianjurkan, karena pengobatan akan bergantung pada penyebab spesifik penyakit.\n" +
            "\n" +
            "Pengobatan infeksi sistemik melibatkan identifikasi penyebab untuk menentukan pengobatan yang tepat. Perawatan luka juga penting, termasuk penggantian air untuk menjaga kualitas air dan, dalam beberapa kasus, penambahan garam akuarium untuk membantu proses penyembuhan. Pemulihan ikan dapat diharapkan setelah parameter kualitas air diperbaiki dan penyebab penyakit diatasi.\n" +
            "\n" +
            "Dalam menghadapi penyakit bercak merah pada ikan, pemahaman mendalam terhadap gejala, penyebab, dan pengobatan merupakan kunci untuk memastikan kesehatan dan kesejahteraan ikan dalam lingkungan akuarium.\n"),
    ArticleItem(R.drawable.fungal, "Fungal disease", "Dalam dunia budidaya perikanan yang dinamis, penyakit jamur seringkali muncul sebagai tantangan serius yang dapat merugikan kesehatan ikan. Jenis jamur seperti Saprolegnia sp. dan Achlya sp. dapat menjadi penyebab infeksi pada ikan, mengakibatkan kerusakan pada kulit dan sirip mereka. Mari kita telusuri penyakit jamur pada ikan secara lebih mendalam, sambil mencermati langkah-langkah penanganan dan pengobatan yang efektif.\n" +
            "\n" +
            "Penyebab:\n" +
            "Penyakit jamur pada ikan sering kali dipicu oleh kondisi lingkungan yang tidak ideal. Kualitas air yang buruk, tingginya kadar bahan organik, dan situasi stres dapat menciptakan lingkungan yang mendukung pertumbuhan jamur. Ikan yang mengalami luka atau kelemahan juga lebih rentan terhadap serangan jamur yang berbahaya.\n" +
            "\n" +
            "Gejala:\n" +
            "Gejala yang muncul akibat penyakit jamur mencakup pembentukan benang-benang halus berwarna putih atau abu-abu pada tubuh ikan, meluasnya luka, dan perubahan perilaku seperti gesekan tubuh yang mencerminkan ketidaknyamanan.\n" +
            "\n" +
            "Penanganan:\n" +
            "Perjalanan pengobatan penyakit jamur dimulai dengan isolasi ikan yang terinfeksi, menghindari penularan ke ikan lain. Upaya peningkatan kualitas air melibatkan pemeliharaan suhu dan oksigen yang optimal, serta penggantian air secara rutin. Memberikan pakan berkualitas tinggi juga menjadi faktor penting untuk memperkuat sistem kekebalan tubuh ikan. Selain itu, mengurangi faktor stres seperti penanganan kasar atau kepadatan ikan yang tinggi dapat meminimalkan risiko infeksi.\n" +
            "\n" +
            "Pengobatan:\n" +
            "Proses pengobatan dilakukan melalui beberapa langkah, mulai dari perendaman dalam larutan garam dapur dengan konsentrasi yang tepat untuk menghambat pertumbuhan jamur. Methylene Blue, dengan sifat anti jamurnya, menjadi pilihan efektif dalam rendaman singkat. Penggunaan obat antijamur komersial seperti Malachite Green atau Formalin juga dapat menjadi solusi efektif dalam mengatasi infeksi jamur.\n" +
            "\n" +
            "Pencegahan:\n" +
            "Tindakan pencegahan menjadi kunci penting dalam menghadapi penyakit jamur pada ikan. Menjaga kebersihan kolam dan peralatan budidaya perikanan, melakukan karantina terhadap ikan baru sebelum dimasukkan ke dalam kolam utama, dan penerapan vaksinasi dapat membantu meningkatkan kekebalan ikan terhadap infeksi jamur yang potensial.\n" +
            "\n" +
            "Sebelum melangkah lebih jauh, konsultasi dengan ahli perikanan adalah langkah bijak untuk memastikan langkah-langkah pengobatan yang tepat dan menghindari efek samping yang mungkin timbul. Dengan pemahaman yang matang tentang penyakit jamur, budidaya perikanan tidak hanya menjadi lebih efisien tetapi juga memastikan kesehatan dan kesejahteraan ikan."),
    ArticleItem(R.drawable.parasit, "Parasitic Disease", "Dalam dunia budidaya perikanan, parasit seringkali menjadi ancaman serius bagi kesehatan ikan, dapat memicu berbagai penyakit yang merugikan produktivitas dan keberlanjutan usaha. Berbagai jenis parasit, seperti protozoa, cacing, dan kutu ikan, dapat menyerang organ-organ penting ikan seperti kulit, insang, dan saluran pencernaan.\n" +
            "\n" +
            "Misalnya, White Spot Disease atau Ichthyophthirius (Ick) ditandai dengan munculnya bintik putih menyerupai garam di kulit dan sirip ikan, disertai perilaku gesekan tubuh. Penanganannya melibatkan isolasi ikan yang terinfeksi dan peningkatan suhu air untuk mempercepat siklus hidup parasit. Pengobatannya melibatkan penggunaan larutan Methylene Blue, garam dapur, atau obat anti-Ich yang direkomendasikan oleh ahli perikanan.\n" +
            "\n" +
            "Gill Flukes atau Dactylogyrus, parasit lainnya, menyebabkan pembengkakan dan perubahan warna pada insang ikan, disertai pernafasan yang cepat. Penanganannya melibatkan peningkatan sirkulasi air dan menjaga kebersihan kolam. Rendaman ikan dengan larutan formalin atau piretrin juga dapat menjadi pilihan pengobatan yang efektif berdasarkan saran ahli perikanan.\n" +
            "\n" +
            "Fish Lice atau Argulus dapat menimbulkan lesi pada kulit ikan, perilaku gesekan tubuh, dan penurunan nafsu makan. Isolasi ikan yang terinfeksi diperlukan untuk mencegah penularan, dan pengobatannya melibatkan penggunaan Methylene Blue atau larutan tembakau sesuai petunjuk ahli perikanan.\n" +
            "\n" +
            "Slime Disease atau Trichodina, yang ditandai dengan produksi lendir berlebihan, luka pada kulit, dan pernafasan cepat, dapat diatasi dengan pemeliharaan air yang optimal dan penggunaan larutan formalin atau garam dapur.\n" +
            "\n" +
            "Upaya preventif, seperti manajemen air yang baik, pemberian pakan berkualitas, dan praktik karantina untuk ikan baru, dapat menjadi kunci dalam mengurangi risiko infeksi parasit. Konsultasi dengan ahli perikanan sangat dianjurkan untuk diagnosis yang akurat dan pengobatan yang tepat. Dengan upaya ini, budidaya perikanan dapat mencapai tingkat keberhasilan yang lebih tinggi, menjaga kesehatan ikan dan produktivitas usaha."),
    ArticleItem(R.drawable.fin_rot, "Fish Fin Rot", "Ikan peliharaan yang mengalami penyakit busuk sirip (Fish Fin Rot) seringkali menunjukkan gejala seperti sirip robek dan kusam, baik pada sirip maupun ekor. Tepi sirip juga dapat menunjukkan perubahan warna yang tidak normal, terkadang lebih terang atau lebih gelap. Jika tidak diobati, kondisi ini dapat menyebabkan kerusakan lebih lanjut pada sirip dan menyebabkan infeksi menyebar ke seluruh tubuh ikan.\n" +
            "Penyebab Busuk Sirip:\n" +
            "Busuk sirip disebabkan oleh infeksi bakteri, seperti Aeromonas dan Pseudomonas. Biasanya, kondisi ini muncul setelah ikan mengalami cedera pada sirip atau ekornya, yang dapat disebabkan oleh pertarungan dengan ikan lain dalam akuarium. Bakteri penyebab busuk sirip umumnya ada di dalam akuarium, tetapi mereka hanya menginfeksi ikan jika ikan tersebut mengalami cedera atau stres. Oleh karena itu, busuk sirip dapat dianggap sebagai infeksi opportunistik.\n" +
            "Pencegahan Busuk Sirip:\n" +
            "    • Pemeliharaan Kualitas Air:\n" +
            "Kualitas air yang buruk dalam akuarium merupakan penyebab umum busuk sirip. Pergantian air parsial secara teratur diperlukan untuk mencegah penyakit. Disarankan untuk melakukan pergantian air setidaknya dua kali seminggu.\n" +
            "    • Pengelolaan Populasi dan Pemberian Makan:\n" +
            "Hindari overpopulasi akuarium dan jangan memberi makan berlebihan pada ikan. Selain itu, hindari menyimpan ikan yang suka menggigit sirip bersama ikan yang memiliki sirip panjang.\n" +
            "Pengobatan Busuk Sirip:\n" +
            "    • Pergantian Air Parsial:\n" +
            "Langkah pertama dalam pengobatan adalah melakukan pergantian air parsial sekitar 40-50%. Pastikan untuk menambahkan penghilang klorin/kloramin ke air baru.\n" +
            "    • Penggunaan Garam Akuarium:\n" +
            "Garam akuarium dapat membantu menghambat pertumbuhan bakteri dan jamur. Gunakan sekitar 1 sendok makan garam akuarium untuk setiap 5 galon air. Campurkan garam dalam larutan air sebelum ditambahkan ke dalam tangki.\n" +
            "    • Penggunaan Melafix atau Bettafix:\n" +
            "Untuk kasus ringan, Melafix atau Bettafix dapat membantu. Namun, untuk kondisi yang lebih parah, perlu pertimbangan pengobatan yang lebih kuat.\n" +
            "\n" +
            "Penting untuk diingat bahwa pencegahan merupakan kunci utama dalam menjaga kesehatan ikan peliharaan. Dengan menjaga kualitas air, mengelola populasi ikan, dan memberikan perawatan yang tepat saat gejala muncul, penyakit busuk sirip dapat dihindari atau diatasi dengan efektif.")
)