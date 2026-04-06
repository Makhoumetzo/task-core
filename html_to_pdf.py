import subprocess
import os

def convert_html_to_pdf(html_path, pdf_path):
    print(f"Converting {html_path} to {pdf_path}...")
    
    # Path to MS Edge (default on Windows)
    edge_path = r"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe"
    
    if not os.path.exists(edge_path):
        print("Error: Edge not found at standard path.")
        return

    # Use Edge headless mode to print to PDF
    # --no-pdf-header-footer to avoid the date/URL trash
    # --print-to-pdf creates the file
    command = [
        edge_path,
        "--headless",
        "--disable-gpu",
        "--run-all-compositor-stages-before-draw",
        "--no-pdf-header-footer",
        f"--print-to-pdf={pdf_path}",
        html_path
    ]

    try:
        subprocess.run(command, check=True)
        print("Success! PDF generated.")
    except subprocess.CalledProcessError as e:
        print(f"Error during conversion: {e}")

if __name__ == "__main__":
    src = r"c:\Users\makhou\IdeaProjects\Rapport_Projet_DevOps_Final.html"
    dest = r"c:\Users\makhou\IdeaProjects\Rapport_Projet_DevOps_Final.pdf"
    convert_html_to_pdf(src, dest)
