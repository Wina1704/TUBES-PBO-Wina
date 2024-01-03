public interface Pesanan {
    String getIdPesanan();
    String getNamaPelanggan();
    String getNomorMeja();
    String getDaftarMenu();
    int getJumlahMenu();
    String getInformasiTambahan();
    void setIdPesanan(String idPesanan);
    void setNamaPelanggan(String namaPelanggan);
    void setNomorMeja(String nomorMeja);
    void setDaftarMenu(String daftarMenu);
    void setJumlahMenu(int jumlahMenu);
    void setInformasiTambahan(String informasiTambahan);
}
