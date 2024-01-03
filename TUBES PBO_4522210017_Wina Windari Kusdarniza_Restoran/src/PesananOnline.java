public class PesananOnline implements Pesanan {
    private String idPesanan;
    private String namaPelanggan;
    private String nomorMeja;
    private String daftarMenu;
    private int jumlahMenu;
    private String informasiTambahan;

    @Override
    public String getIdPesanan() {
        return idPesanan;
    }

    @Override
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    @Override
    public String getNomorMeja() {
        return nomorMeja;
    }

    @Override
    public String getDaftarMenu() {
        return daftarMenu;
    }

    @Override
    public int getJumlahMenu() {
        return jumlahMenu;
    }

    @Override
    public String getInformasiTambahan() {
        return informasiTambahan;
    }

    @Override
    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    @Override
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    @Override
    public void setNomorMeja(String nomorMeja) {
        this.nomorMeja = nomorMeja;
    }

    @Override
    public void setDaftarMenu(String daftarMenu) {
        this.daftarMenu = daftarMenu;
    }

    @Override
    public void setJumlahMenu(int jumlahMenu) {
        this.jumlahMenu = jumlahMenu;
    }

    @Override
    public void setInformasiTambahan(String informasiTambahan) {
        this.informasiTambahan = informasiTambahan;
    }
}

